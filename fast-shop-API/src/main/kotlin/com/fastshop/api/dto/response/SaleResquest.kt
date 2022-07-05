package com.fastshop.api.dto.response

import com.fastshop.api.dto.input.ProductSaleInput

data class SaleResquest(
    val status:String="waiting for confirmation",
    val clientType:String,
    val paymentMode:String,
    val products:List<ProductSaleInput>,
//    links ="[ /id/confirm; ]'
)
