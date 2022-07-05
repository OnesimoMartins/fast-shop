package com.fastshop.domain.service

import com.fastshop.domain.model.Product
import com.fastshop.domain.repository.ProductRepository
import org.springframework.stereotype.Service

//@Service
class ProductService(val productRepository: ProductRepository) {
    //    fun createProduct
//            fun getProducById
//                    fun deleteBroguct
//                            fun
//
    //criar eneveto ao vender produto diminui o stok
    fun createProduct(product: Product) :Product= productRepository.save(product)
}


