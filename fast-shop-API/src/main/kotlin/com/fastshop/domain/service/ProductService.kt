package com.fastshop.domain.service

import com.fastshop.domain.model.Product
import com.fastshop.domain.repository.ProductRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryService: CategoryService
) {
    fun updateProduct(product:Product): Product {
     findProductById(product.id!!)
        return createProduct(product)
    }

    fun createProduct(product: Product): Product = product.let {
       val category= categoryService.findCategoryByIdOrFail(it.category!!.id!!)
        it.category=category
        productRepository.save(it)
    }

    fun findProductById(id: Long): Product = productRepository.findById(id).orElseThrow {
        EntityNotFoundException( "No Product with id $id was found")
    }

    fun increaseStrock(id:Long,quantity:Int): Product {
        val product=findProductById(id)
        product.increaseQuantity(quantity)
        return productRepository.save(product)
    }

    fun deleteProductById(id: Long) {
        this.productRepository.delete(findProductById(id))
    }
}


