package com.fastshop.api.v1.dto.input

import com.fasterxml.jackson.annotation.JsonProperty
import com.fastshop.domain.model.PaymentMode
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class SaleInput(

    @field:NotNull
    @JsonProperty("payment_mode")
    val paymentMode: PaymentMode,

    @field:Valid
    @field:Size(min = 1, message = "A sale must have at least an item ")
    val itens:List<SaleItemInput>
)