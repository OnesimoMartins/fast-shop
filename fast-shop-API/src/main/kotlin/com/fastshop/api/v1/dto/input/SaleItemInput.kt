package com.fastshop.api.v1.dto.input

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.ToString
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@ToString
data class SaleItemInput(

    @field:NotNull(message = "Insert the product id")
    @JsonProperty("product_id")
    val productId:Long,

    @field:Min(value = 1, message = "Must have at least '1' in quantity field")
    val quantity:Int
    )
