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
    ).add(productListLink()).add(productLink(it.id!!)).add(productStockLink(it.id!!))
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

fun toCategoryPagedModel(categories: Page<Category>): PagedModel<CategoryModel> {
    val content=categories.content.map { toCategoryModel(it) }.toList()


    val page=  PagedModel.of(content,PagedModel.PageMetadata(
        categories.size.toLong(),
        categories.number.toLong()
        ,categories.totalElements,categories.totalPages.toLong()
    ))


    //next
    if(!categories.isLast)
       page.add(
           nextOrPreviusPageCategories( page = categories.number+1
                             ,size=categories.size).withRel("next")
       )
    //previous
    if(!categories.isFirst)
        page.add(
            nextOrPreviusPageCategories( page = categories.number-1
                ,size=categories.size).withRel("previous")
        )


    return  page

}


fun toProductPagedModel(products: Page<Product>,
                        justAvailables:Boolean): PagedModel<ProductModel> {
    val content=products.content.map { toProductModel(it) }.toList()

    val page=  PagedModel.of(content,PagedModel.PageMetadata(
        products.size.toLong(),
        products.number.toLong()
        ,products.totalElements,products.totalPages.toLong()
    ))

    //next
    if(!products.isLast)
        page.add(
            if(justAvailables)
                nextOrPreviusPageProductsAvailables(page = products.number+1
                    ,size=products.size).withRel("next")
                        else
            nextOrPreviusPageProducts( page = products.number+1
                ,size=products.size).withRel("next")
        )
    //previous
    if(!products.isFirst)
        page.add(
            if(justAvailables)
                nextOrPreviusPageProductsAvailables(page = products.number-1
                    ,size=products.size).withRel("previous")
            else
            nextOrPreviusPageProducts( page = products.number-1
                ,size=products.size).withRel("previous")
        )

    return  page
}

