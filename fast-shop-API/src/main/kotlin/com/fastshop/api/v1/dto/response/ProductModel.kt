package com.fastshop.api.v1.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fastshop.domain.model.MeasurementUnity
import lombok.Builder
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal

@Builder
data class ProductModel(
    val id:Long,
    val name:String,
    val description:String,
    val category: CategoryModel,
    @JsonProperty("available_unities")
    val availableUnities:Long,
    @JsonProperty("measurement_unit")
    val measurementUnit: MeasurementUnity,
    val price: BigDecimal,
    @JsonProperty("purchise_price")
    val purchisePrice: BigDecimal,
    ):RepresentationModel<ProductModel>()
