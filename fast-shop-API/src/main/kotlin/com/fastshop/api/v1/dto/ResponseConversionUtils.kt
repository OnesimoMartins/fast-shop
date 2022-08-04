package com.fastshop.api.v1.dto

import com.fastshop.api.v1.dto.response.*
import com.fastshop.core.utils.isPositive
import com.fastshop.core.utils.isPositiveOrZero
import com.fastshop.domain.model.Category
import com.fastshop.domain.model.Product
import com.fastshop.domain.model.Sale
import com.fastshop.domain.model.SaleItem
import org.springframework.data.domain.Page
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.PagedModel

fun toCategoryModel(category: Category): CategoryModel = category.let {
    val model = CategoryModel(it.id!!, it.name!!)

    model.add(categorieLink(it.id))
    model.add(categoriesLink())
    model
}

fun toCategorieCollectionModel(categories: List<Category>): CollectionModel<CategoryModel> =
    categories.let {

        val categoriesModel = ArrayList<CategoryModel>()
        it.forEach { m ->
            run {
                categoriesModel.add(CategoryModel(m.id!!, m.name!!).add(categorieLink(m.id)))
            }
        }

        return CollectionModel.of(categoriesModel).removeLinks()
    }

fun toSaleModel(sale: Sale): SaleModel {
    val model = SaleModel(
        id = sale.id!!,
        status = sale.status,
        paymentMode = sale.paymentMode,
        // if transshipment is -1 means that sale wasn't confirmed and transhipment is unknoun
        transshipment = sale.transshipment.takeIf { it.isPositiveOrZero() },
        amountPaid = sale.amountPaid.takeIf { it.isPositive() },
        total = sale.total.takeIf { it.isPositive() },

        date = sale.date!!,
        saleItens = sale.saleItens.map { toSaleItemModel(it) }
    )
    model.add(comfirmSaleLink(model.id))
    model.add(cancelSaleLink(model.id))

    return model

}

fun toConfirmedSaleModel(sale: Sale): SaleModel {
    return SaleModel(
        id = sale.id!!,
        status = sale.status,
        paymentMode = sale.paymentMode,
        // if transshipment is -1 means that sale wasn't confirmed and transhipment is unknoun
        transshipment = sale.transshipment,
        amountPaid = sale.amountPaid,
        total = sale.total,
        date = sale.date!!,
        saleItens = sale.saleItens.map { toConfirmedSaleItemModel(it) }
    )
}

fun toSaleItemModel(saleItem: SaleItem): SaleItemModel = saleItem.let {
    val itemModel = toConfirmedSaleItemModel(it)
    itemModel.add(updateItemQuantity(saleItem.sale!!.id!!, saleItem.id!!))
    itemModel.add(removeSaleItem(saleItem.sale!!.id!!, saleItem.id))
    return@let itemModel
}

fun toConfirmedSaleItemModel(saleItem: SaleItem): SaleItemModel = saleItem.let {
    val resumedProduct = toResumedProductModel(it.product)
    val itemModel = SaleItemModel(
        saleItem.id!!, quantity = saleItem.quantity,
        product = resumedProduct,
        total = saleItem.total!!
    )
    itemModel
}

fun toResumedProductModel(product: Product) =
    ResumedProductModel(
        id = product.id!!,
        name = product.name,
        categoryModel = toCategoryModel(product.category!!),
        price = product.price
    )

fun toProductModel(product: Product): ProductModel = product.let {
    ProductModel(
        id = it.id!!,
        name = it.name,
        description = it.description,
        category = toCategoryModel(it.category!!),
        availableUnities = it.availableUnities!!,
        measurementUnit = it.measurementUnit!!,
        price = it.price,
        purchisePrice = it.purchisePrice
    ).add(productListLink()).add(productLink(it.id))
}

fun toProductModelCollection(products: MutableCollection<Product>): CollectionModel<ProductModel> =
    products.let {
        val products = ArrayList<ProductModel>()
        it.forEach { m ->
            m.run {
                products.add(toProductModel(m))
            }
        }
        CollectionModel.of(products)
    }

fun toProductPageModel(products: Page<Product>): PagedModel<ProductModel> {
  val content=products.content.map { toProductModel(it) }.toList()

    return  PagedModel.of(content,PagedModel.PageMetadata(
      products.size.toLong(),
      products.number.toLong()
      ,products.totalElements,products.totalPages.toLong()
  ))

}
