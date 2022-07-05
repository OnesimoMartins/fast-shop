package com.fastshop.api.dto.input

import com.fasterxml.jackson.annotation.JsonProperty
import com.fastshop.domain.model.MeasurementUnity
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class ProductInput (
    @NotBlank
    val name:String,
    val description:String,
    @JsonProperty("category_id")
    @Positive
    val categoryId:Int,
    @NotBlank
    @JsonProperty("measurement_unit")
    val measurementUnit:MeasurementUnity,
    @Positive
    @NotNull
    val price:BigDecimal,
    @Positive
    @NotNull
    @JsonProperty("reseller_price")
    val resellerPrice:BigDecimal,
    @Positive
    @NotNull
    @JsonProperty("purchise_price")
    val purchisePrice:BigDecimal,
//    val image:String
//    val
        ){

    override fun toString(): String =" name= $name  \n description=$description " +
            "\n category_id=$categoryId \n measurement_Unit=$measurementUnit " +
            "\n price=$price"
}
