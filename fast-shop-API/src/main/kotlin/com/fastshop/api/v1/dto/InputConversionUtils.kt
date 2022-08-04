package com.fastshop.api.v1.dto


import com.fastshop.api.v1.dto.input.CategoryInput
import com.fastshop.api.v1.dto.input.ProductInput
import com.fastshop.api.v1.dto.input.SaleInput
import com.fastshop.api.v1.dto.input.SaleItemInput
import com.fastshop.domain.model.*
import java.math.BigDecimal

fun toSale(saleInput: SaleInput): Sale = saleInput.let {

    return@let Sale(
        paymentMode = it.paymentMode,
        saleItens = toSaleItemList(it.itens),
        transshipment = BigDecimal("-1")
    )
}

fun toCategory(categoryInput: CategoryInput): Category = Category(name = categoryInput.name)

fun toSaleItemList(saleItensInput: List<SaleItemInput>): MutableList<SaleItem> =
    saleItensInput.let {

        fun converAsaleInput(saleItemInput: SaleItemInput) =
            SaleItem(quantity = saleItemInput.quantity, product = Product(saleItemInput.productId))

        return@let saleItensInput.stream().map { input ->converAsaleInput(input) }.toList()
    }

fun toProduct(productInput: ProductInput) = productInput.run {
    return@run Product(
        name = this.name,
        description= if(description.length>10) description else "Sem descrição",
        category = Category(this.categoryId),
        measurementUnit = this.measurementUnit,
        price = this.price,
        purchisePrice = this.purchisePrice
    )
}
