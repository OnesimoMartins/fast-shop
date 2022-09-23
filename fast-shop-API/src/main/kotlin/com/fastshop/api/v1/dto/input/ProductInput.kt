package com.fastshop.api.v1.dto.input

import com.fasterxml.jackson.annotation.JsonProperty
import com.fastshop.domain.model.MeasurementUnity
import lombok.ToString
import java.math.BigDecimal
import javax.validation.constraints.*

@ToString
data class ProductInput (

    @field:NotBlank
    val name:String,

    val description:String= "Sem descrição",

    @JsonProperty("category_id")
    @field:Positive
    val categoryId:Int,

    @JsonProperty("measurement_unit")
    val measurementUnit:MeasurementUnity,

    @field:Positive
    @field:NotNull
    val price:BigDecimal,

    @field:Positive
    @field:NotNull
    @JsonProperty("purchise_price")
    val purchisePrice:BigDecimal,
)