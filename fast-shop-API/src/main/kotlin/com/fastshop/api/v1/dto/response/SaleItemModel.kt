package com.fastshop.api.v1.dto.response


import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal

data class SaleItemModel(
val id:Long,
val quantity:Int,
val total: BigDecimal ,
val product: ResumedProductModel,
):RepresentationModel<SaleModel>()