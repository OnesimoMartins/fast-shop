package com.fastshop.api.v1.controller

import com.fastshop.api.v1.dto.input.SaleConfirmationInput
import com.fastshop.api.v1.dto.input.SaleInput
import com.fastshop.api.v1.dto.response.SaleModel
import com.fastshop.api.v1.dto.toConfirmedSaleModel
import com.fastshop.api.v1.dto.toSale
import com.fastshop.api.v1.dto.toSaleModel
import com.fastshop.domain.filter.SalesFilter
import com.fastshop.domain.model.Sale
import com.fastshop.domain.repository.SaleRepository
import com.fastshop.domain.service.SaleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin
@RequestMapping("/sales")
class SalesController(
    private val salesService: SaleService,
    val saleRepository: SaleRepository
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createSale(@Valid @RequestBody saleInput: SaleInput) = saleInput.let {
        val sale = salesService.createSale(toSale(it))
        return@let toSaleModel(sale)
    }

    @PutMapping("/{id}/confirm")
    fun confirmSale(@PathVariable id: Long,
                    @Valid @RequestBody confirmation: SaleConfirmationInput
    ): SaleModel = toConfirmedSaleModel(   salesService.confirmSale(id,confirmation.amountPaid)  )


    @PutMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancelSale(@PathVariable id: Long) =salesService.cancelSale(id)


    @GetMapping("/{id}")
    fun saleById(@PathVariable id:Long)= toSaleModel( salesService.findSaleByIdOrFail(id))

    @GetMapping
    fun allSales(@RequestParam tfilter: SalesFilter):
            MutableList<Sale> = saleRepository.findAll()

}