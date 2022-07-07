package com.fastshop.api.dto

import com.fastshop.api.dto.input.ProductInput
import com.fastshop.api.dto.input.SaleItemInput
import com.fastshop.domain.model.*
import com.fastshop.domain.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired

//fun convertSaleInputToSale(saleInput: SaleInput):Sale = saleInput.let {
//    return@let Sale(
//        products = convertSaleProductInputListToProdctList(it.products),
//        clientType = it.clientType,
//        amountPaid = it.amountPaid
//    )
//}


fun convertSaleItemInputToProdct(productInput: SaleItemInput): Product =
    productInput.let {
        Product(id = it.productId)
    }

private fun convertSaleProductInputListToProdctList(productSalesInput: List<SaleItemInput>) = productSalesInput.let {
    val productsList = ArrayList<Product>()
//    it.forEach { it -> productsList.add(convertSaleProductInputToProdct(it)) }
    productsList
}

fun convertProductInputToProduct(productInput: ProductInput) = productInput.run {
    Product(
        name = this.name,
        category = Category(this.categoryId),
        measurementUnit = this.measurementUnit,
        price = this.price,
        resellerPrice = this.resellerPrice,
        purchisePrice = this.purchisePrice
    )
}
//fun convertProductToProductResponse TODO hateoas