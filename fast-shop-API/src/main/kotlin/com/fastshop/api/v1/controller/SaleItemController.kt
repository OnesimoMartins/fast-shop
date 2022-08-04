package com.fastshop.api.v1.controller

import com.fastshop.api.v1.dto.response.SaleModel
import com.fastshop.api.v1.dto.toSaleModel
import com.fastshop.domain.service.SaleService
import org.springframework.web.bind.annotation.*

@RequestMapping("sales/{saleId}/itens")
@RestController
class SaleItemController(val saleService: SaleService) {

    @DeleteMapping("/{itemId}")
    fun removeItem(@PathVariable saleId: Long, @PathVariable itemId:Long)=
     toSaleModel(   saleService.deleteSaleItem(saleId, itemId) )

    @PutMapping("/{itemId}")
    fun updateQuantity(@PathVariable itemId:Long,@PathVariable saleId:Long,@RequestParam(required = true)
    quantity:Int):
            SaleModel {

        val updatedSale= saleService.updateItemQunatity(saleId,itemId,quantity)
        return toSaleModel( updatedSale)
    }
}