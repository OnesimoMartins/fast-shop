package com.fastshop.api.v1.dto.input

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import javax.validation.constraints.Positive

data class SaleConfirmationInput (
    @field:Positive
    @JsonProperty("amount_paid")
    val amountPaid: BigDecimal)