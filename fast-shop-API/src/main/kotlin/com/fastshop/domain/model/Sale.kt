package com.fastshop.domain.model

import org.hibernate.annotations.BatchSize
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.stream.Collectors
import javax.persistence.*

@Entity
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,

    @Enumerated(EnumType.STRING)
    var status: SaleStatus = SaleStatus.CREATED,

    @Enumerated(EnumType.STRING)
    val clientType: ClientType,

    @Enumerated(EnumType.STRING)
    val paymentMode: PaymentMode,
    
    val amountPaid: BigDecimal,

    val transshipment: BigDecimal = BigDecimal.ZERO,// TODO rever

    val total: BigDecimal = BigDecimal.ZERO,

    @Column(insertable = false, updatable = false)
    val date: LocalDateTime? = null,

    @OneToMany(fetch = FetchType.LAZY//, mappedBy = "sale", cascade = [CascadeType.MERGE]
        , cascade = [CascadeType.ALL])//TODO pesquisa mappedby,cascadetypes
    @JoinColumn(name = "sale_id")

    //,mappedBy = "sale")
    @BatchSize(size = 15)
//    @JoinTable(
//        name = "sale_item")
//        joinColumns = [JoinColumn(name = "sale_id")],
//        inverseJoinColumns = [JoinColumn(name = "product_id")]
//    )
    val saleItens: List<SaleItem>,
) {
    fun calculateTotal(): BigDecimal =
        this.total.add(this.saleItens.map { item ->
            item.product.price.multiply(BigDecimal(item.quantity))
        }
            .stream().collect(Collectors.reducing { u, prox -> u.add(prox) })
            .get()
        )

}