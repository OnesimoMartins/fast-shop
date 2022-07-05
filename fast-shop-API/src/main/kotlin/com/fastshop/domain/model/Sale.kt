package com.fastshop.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.stream.Collectors
import javax.persistence.*

data class Sale (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0,
    val products:ArrayList<Product>,//TODO not empty --rever
    @Enumerated(EnumType.STRING)
    var status:SaleStatus=SaleStatus.CREATED,
    val clientType:ClientType,
    val amountPaid:BigDecimal= BigDecimal.ONE,
    val transshipment:BigDecimal=BigDecimal.ZERO,// TODO rever
    val total:BigDecimal=products.let{it->it.stream().map {it.price}
        .collect(Collectors.reducing { t, u -> t.add(u) }).get()
    },
    val date: LocalDateTime?=null
){
   // fun confirmSale()= status=SaleStatus.CONFIRMED
}