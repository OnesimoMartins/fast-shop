package com.fastshop.api.dto.input

import com.fastshop.domain.model.ClientType
import com.fastshop.domain.model.PaymentMode
import java.math.BigDecimal

data class SaleInput(
    val amountPaid:BigDecimal,
    val clientType:ClientType,
    val paymentMode: PaymentMode,
    val products:List<ProductSaleInput>
)
