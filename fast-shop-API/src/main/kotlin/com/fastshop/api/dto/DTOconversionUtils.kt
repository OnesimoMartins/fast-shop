package com.fastshop.api.dto

import com.fastshop.api.dto.input.ProductInput
import com.fastshop.api.dto.input.ProductSaleInput
import com.fastshop.api.dto.input.SaleInput
import com.fastshop.domain.model.*

fun convertSaleInputToSale(saleInput: SaleInput)= saleInput.let {
    val sale=Sale(products=convertSaleListProductInputToProdctList(it.products),
                  clientType = it.clientType,
                  amountPaid = it.amountPaid)
    it.products.forEach { product->
        sale.products.add(convertSaleProductInputToProdct(product))
    }
    return@let sale
}

fun convertSaleProductInputToProdct(productInput: ProductSaleInput): Product
=Product(productInput.productId,"","",null,MeasurementUnity.M)//TODO REVER

private fun convertSaleListProductInputToProdctList(productSalesInput: List<ProductSaleInput>)=
    productSalesInput.let {
        val productsList=ArrayList<Product>()
        it.forEach { it-> productsList.add(convertSaleProductInputToProdct(it)) }
        productsList
    }

fun convertProductInputToProduct(productInput:ProductInput)=productInput.run {
    Product(name = this.name, category = Category(this.categoryId),
        measurementUnit = this.measurementUnit, price = this.price,
    resellerPrice = this.resellerPrice, purchasePrice = this.purchisePrice)
}
//fun convertProductToProductResponse TODO hateoas