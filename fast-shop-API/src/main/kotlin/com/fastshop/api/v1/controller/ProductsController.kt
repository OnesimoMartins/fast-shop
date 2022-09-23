package com.fastshop.api.v1.controller

import com.fastshop.api.v1.dto.*
import com.fastshop.api.v1.dto.input.ProductInput
import com.fastshop.api.v1.dto.response.ProductModel
import com.fastshop.domain.repository.ProductRepository
import com.fastshop.domain.service.ProductService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin
@RequestMapping("products")
@RestController
class ProductsController(
    private val productRepository: ProductRepository,
    private val productService: ProductService
) {
    @Deprecated("to be removed on next version")
    @GetMapping("/all")
    fun allProducts(): CollectionModel<ProductModel> =
        toProductModelCollection(productRepository.findAll())

    @GetMapping
    fun allProducts(
        @PageableDefault(page = 0, value = 5)
        pageable: Pageable,
        @RequestParam(required = false, defaultValue = "false") available: Boolean
    ): PagedModel<ProductModel> {

        val result = if (available)
            productRepository.findAvailableProducts(pageable)
        else
            productRepository.findAll(pageable)

        return toProductPagedModel(result, available)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody @Valid productInput: ProductInput) = productInput.let {
        val product = toProduct(it)
        productService.createProduct(product)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun updateProduct(@RequestBody @Valid productInput: ProductInput, @PathVariable id: Long)
    = productInput.let {
        val product = toProduct(it)
        product.id = id
        productService.updateProduct(product)
    }

    @PutMapping("/stock")
    fun increaseStrock(
        @RequestParam(value = "id", required = true) productId: Long,
        @RequestParam(value = "quantity", required = true) quantity: Int
    ) = toProductModel(productService.increaseStrock(quantity = quantity, id = productId))

    @GetMapping("/{id}")
    fun productById(
        @PathVariable id: Long,
    ) = toProductModel(this.productService.findProductById(id))

    @DeleteMapping("/{id}")
    fun deleteProductById(
        @PathVariable id: Long,
    ) = this.productService.deleteProductById(id)

}