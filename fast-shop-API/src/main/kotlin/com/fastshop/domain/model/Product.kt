package com.fastshop.domain.model

import com.fastshop.domain.exception.BusinessException
import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id:Long?=null,
    val name:String="",
    val description:String="",
    @OneToOne
    var category:Category?=null,
    var availableUnities:Long?=0,
    @Enumerated(EnumType.STRING)
    val measurementUnit: MeasurementUnity?=null,

    val price:BigDecimal= BigDecimal.ZERO,
    val purchisePrice:BigDecimal= BigDecimal.ZERO,
    ){

    fun increaseQuantity(qt:Int){
        if(qt<1) throw BusinessException("Invalid Stock quntity")
        this.availableUnities = this.availableUnities?.plus(qt)
    }

}