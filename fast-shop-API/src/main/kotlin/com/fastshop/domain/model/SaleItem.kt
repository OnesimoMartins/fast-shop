package com.fastshop.domain.model

import lombok.ToString
import java.math.BigDecimal
import java.util.stream.Collectors
import javax.persistence.*

@ToString
@Entity
data class SaleItem(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id:Long?=null,

    var quantity:Int,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name="product_id")
    var product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    @ToString.Exclude
    var sale: Sale?=null,

    var total:BigDecimal?=null
){
    fun calculateTotal(): BigDecimal {
        total=this.product.price.multiply(BigDecimal(this.quantity))
        return total!!
    }
}