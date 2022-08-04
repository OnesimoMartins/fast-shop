package com.fastshop.api.v1.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class ResumedProductModel(
    val id: Long,
    val name:String,
    val price:BigDecimal,
    @JsonProperty("category")
    val categoryModel: CategoryModel,
    )