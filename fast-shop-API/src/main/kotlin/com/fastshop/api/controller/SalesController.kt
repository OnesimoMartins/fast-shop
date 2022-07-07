package com.fastshop.api.controller

import com.fastshop.api.dto.input.SaleInput
import com.fastshop.domain.repository.SaleRepository
import com.fastshop.domain.service.SaleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sales")
class SalesController(private val salesService: SaleService,
                      val saleRepository: SaleRepository
){

//    TODO PUT HATEOAS   links ="[ /id/confirm; /id/cancel ]; /id/remove-products ;"
    @PostMapping
     fun createSale(saleInput: SaleInput)=""
//         saleInput.let { return@let(convertSaleInputToSale(saleInput)) }

    @GetMapping
    fun allSales()=saleRepository.findAll()
//    @PutMapping("{id}/confirm")
//    fun confirmSale( @PathVariable saleId: Long)=salesService.confirmSale(saledId)
}