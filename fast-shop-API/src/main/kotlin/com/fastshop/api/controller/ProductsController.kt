package com.fastshop.api.controller

import com.fastshop.api.dto.convertProductInputToProduct
import com.fastshop.api.dto.input.ProductInput
import com.fastshop.domain.model.MeasurementUnity
import com.fastshop.domain.repository.ProductRepository
import org.springframework.web.bind.annotation.*

@RequestMapping("products")
@RestController
class ProductsController(private val productRepository: ProductRepository) {

    @GetMapping
    fun allProducts() =productRepository.findAll()

    @PostMapping
    fun createProduct( @RequestBody productInput : ProductInput)=
        convertProductInputToProduct(productInput)

}