package com.fastshop.api.v1.controller

import com.fastshop.api.v1.dto.input.ProductInput
import com.fastshop.api.v1.dto.response.ProductModel
import com.fastshop.api.v1.dto.toProduct
import com.fastshop.api.v1.dto.toProductModel
import com.fastshop.api.v1.dto.toProductModelCollection
import com.fastshop.api.v1.dto.toProductPageModel
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
    @Deprecated("to me removed on next version")
    @GetMapping("/all")
    fun allProducts(): CollectionModel<ProductModel> =
        toProductModelCollection( productRepository.findAll())

    @GetMapping
    fun allProducts(@PageableDefault(page = 0, value = 5)
                    pageable: Pageable): PagedModel<ProductModel> {

        return toProductPageModel(productRepository.findAll(pageable))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody @Valid productInput: ProductInput) = productInput.let {
        var product = toProduct(it)
        productService.createProduct(product)
    }
    @GetMapping("/{id}")
    fun productById(@PathVariable id:Long)= toProductModel( this.productService.findProductById(id) )
}