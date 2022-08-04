package com.fastshop.domain.model

import com.fastshop.core.utils.isPositive
import com.fastshop.domain.exception.SaleAlreadyCanceledException
import com.fastshop.domain.exception.SaleAlreadyConfirmedException
import com.fastshop.domain.exception.SaleItemNotFoundException
import com.fastshop.domain.exception.UnsufficientMoneyException
import lombok.ToString
import org.hibernate.annotations.BatchSize
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.stream.Collectors
import javax.persistence.*

@Entity
@ToString
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    var status: SaleStatus = SaleStatus.CREATED,

    @Enumerated(EnumType.STRING)
    val paymentMode: PaymentMode,

    var amountPaid: BigDecimal = BigDecimal.ZERO,

    var transshipment: BigDecimal = BigDecimal.ZERO,

    var total: BigDecimal = BigDecimal.ZERO,

    @Column(insertable = false, updatable = false)
    var date: LocalDateTime? = null,

    @OneToMany(
        fetch = FetchType.LAZY, cascade = [CascadeType.ALL]
    )//TODO pesquisa mappedby,cascadetypes
    @JoinColumn(name = "sale_id")
    @BatchSize(size = 15)
    var saleItens: MutableList<SaleItem>,
) {
    fun calculateTotal() {
        this.total = this.saleItens.stream().map { item -> item.calculateTotal() }
            .collect(Collectors.reducing { u, prox -> u.add(prox) }).get()
    }

    fun confirmSale(givenMoney: BigDecimal): Sale {

        if (this.isConfirmed())
            throw  SaleAlreadyConfirmedException(this.id!!, this.date!!)

        if (this.isCanceled())
            throw  SaleAlreadyCanceledException(this.id!!, this.date!!)

        this.amountPaid=givenMoney
        val subtraction = this.amountPaid.subtract(this.total)

        if (!subtraction.isPositive())
            throw UnsufficientMoneyException(subtraction.abs())

        this.transshipment=subtraction

        this.status = SaleStatus.CONFIRMED
        this.saleItens.forEach { it.product.availableUnities = it.product.availableUnities!!.minus(it.quantity) }

        return this
    }

    fun cancelSale(): Sale {

        if (this.status == SaleStatus.CANCELED)
            throw  SaleAlreadyCanceledException(this.id!!, this.date!!)

        if (this.status == SaleStatus.CONFIRMED)
            throw  SaleAlreadyConfirmedException(this.id!!, this.date!!)

        this.status = SaleStatus.CANCELED
        return this
    }

    fun setItemQuantity(id: Long, quantity: Int): SaleItem {
        val saleItem = findItemById(id)

        if (this.isConfirmed())
            throw  SaleAlreadyConfirmedException(this.id!!, this.date!!)

        if (this.isCanceled())
            throw  SaleAlreadyCanceledException(this.id!!, this.date!!)

        saleItem.quantity = quantity

        return saleItem
    }

    fun isConfirmed() = this.status == SaleStatus.CONFIRMED

    fun isCanceled() = this.status == SaleStatus.CANCELED

    private fun findItemById(id: Long): SaleItem = this.saleItens.stream().filter { it.id == id }.findFirst()
        .orElseThrow { SaleItemNotFoundException("This sale doesn't have this item") }

}