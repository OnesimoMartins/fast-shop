package com.fastshop.api.v1.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fastshop.domain.model.PaymentMode
import com.fastshop.domain.model.SaleStatus
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import java.time.LocalDateTime

data class SaleModel(
    val id: Long,
    val status: SaleStatus,

    @JsonProperty("payment_mode")
    val paymentMode: PaymentMode,

    @JsonProperty("amount_paid")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val amountPaid: BigDecimal?=null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val transshipment: BigDecimal?=null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val total: BigDecimal?=null,
    val date: LocalDateTime,
    @JsonProperty("itens")
    val saleItens: List<SaleItemModel>
):RepresentationModel<SaleModel>()