package com.fastshop.api.controller

import com.fastshop.api.dto.convertSaleInputToSale
import com.fastshop.api.dto.input.SaleInput
import com.fastshop.domain.service.SalesFlowService
import com.fastshop.domain.service.SalesService
import org.springframework.web.bind.annotation.*

//@RestController
@RequestMapping("/sales")
class SalesController( private val salesService: SalesService,
                       private val salesFlowService: SalesFlowService
){

//    TODO PUT HATEOAS   links ="[ /id/confirm; /id/cancel ]; /id/remove-products ;"
    @PostMapping
     fun createSale(saleInput: SaleInput)=
         saleInput.let { return@let(convertSaleInputToSale(saleInput)) }

//    @PutMapping("{id}/confirm")
//    fun confirmSale( @PathVariable saleId: Long)=salesService.confirmSale(saledId)
}