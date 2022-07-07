package com.fastshop.domain.service

import com.fastshop.domain.model.Product
import com.fastshop.domain.repository.CategoryRepository
import com.fastshop.domain.repository.ProductRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {
    //    fun createProduc
//                    fun deleteBroguct

    //criar eneveto ao vender produto diminui o stok TODO
    fun createProduct(product: Product): Product = product.let {
        categoryRepository.findById(it.category!!.id)
            .orElseThrow { EntityNotFoundException("No Category with id ${it.category.id} was found") }
        productRepository.save(it)
    }

    fun findProductById(id: Long): Product = productRepository.findById(id).orElseThrow {
        EntityNotFoundException(
        "No Product with id $id wasn't found"
        )
    }
}


