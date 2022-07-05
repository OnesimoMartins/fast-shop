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
    val category:Category?=null,

    @Enumerated(EnumType.STRING)
    val measurementUnit: MeasurementUnity,

    val price:BigDecimal= BigDecimal.ZERO,
    val resellerPrice:BigDecimal= BigDecimal.ZERO,
    val purchasePrice:BigDecimal= BigDecimal.ZERO
    )