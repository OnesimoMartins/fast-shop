package com.fastshop.api.v1.dto

import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.Link


// TODO solve problem with kotlin reflection 'methodOn'
//catedorie links
fun categoriesLink() = Link.of("http://localhost:8080/categories").withRel(IanaLinkRelations.COLLECTION)
fun categorieLink(id: Int) = Link.of("http://localhost:8080/categories/${id}").withRel(IanaLinkRelations.SELF)

//sale Links
fun saleLink(id: Long) = Link.of("http://localhost:8080/sales/${id}","self")
fun comfirmSaleLink(id: Long):Link= Link.of("http://localhost:8080/sales/${id}/confirm","comfirmation")
fun cancelSaleLink(id: Long) = Link.of("http://localhost:8080/sales/${id}/cancel").withRel(IanaLinkRelations.SELF)

fun addNewItem(saleId: Long) = Link.of("http://localhost:8080/sales/${saleId}/remove-item/").withRel(IanaLinkRelations.SELF)

//sale item links
fun updateItemQuantity(saleId: Long,itemId:Long) = Link.of("http://localhost:8080/sales/${saleId}/itens/$itemId?quantity=").withRel("update_quantity")
fun removeSaleItem(saleId: Long,itemId:Long) = Link.of("http://localhost:8080/sales/${saleId}/itens/$itemId").withRel("remove_item")

//product links
fun productListLink() = Link.of("http://localhost:8080/products").withRel(IanaLinkRelations.COLLECTION)
fun productLink(id: Long) = Link.of("http://localhost:8080/products/${id}").withRel(IanaLinkRelations.SELF)
// add stock