package com.fastshop.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id:Long?=null,
    val name:String="",
    val description:String="",
    @OneToOne
    var category:Category?=null,
    var availableUnities:Long?=0,
    @Enumerated(EnumType.STRING)
    val measurementUnit: MeasurementUnity?=null,

    val price:BigDecimal= BigDecimal.ZERO,
    val purchisePrice:BigDecimal= BigDecimal.ZERO,
    )