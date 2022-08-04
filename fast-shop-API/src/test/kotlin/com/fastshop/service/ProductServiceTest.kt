package com.fastshop.service

import com.fastshop.domain.model.Category
import com.fastshop.domain.model.MeasurementUnity
import com.fastshop.domain.model.Product
import com.fastshop.domain.service.ProductService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import javax.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions as AssertionsJ

@SpringBootTest
class ProductServiceTest{

    @Autowired lateinit var productService: ProductService
    @Test
    fun `should create a new product successfully`(){
        var newProduct= Product(
            name = "lagosta",
            description = "petisco marinho",
            category = Category(1),
            measurementUnit = MeasurementUnity.kg,
            price = BigDecimal("5000"),
            purchisePrice = BigDecimal("4300")
        )
        newProduct= productService.createProduct(newProduct)
        Assertions.assertThat( newProduct).isNotNull
        Assertions.assertThat( newProduct.id).isGreaterThan(0)

    }
    @Test
    fun `should not create a new product when category id is not found`(){
        var newProduct= Product(
            name = "lagosta",
            description = "petisco marinho",
            category = Category(0),
            measurementUnit = MeasurementUnity.kg,
            price = BigDecimal("5000"),
            purchisePrice = BigDecimal("4300")
        )
       AssertionsJ.assertThrows(EntityNotFoundException::class.java){
            productService.createProduct(newProduct)
        }
    }
    // create a new product

    //validate product
}