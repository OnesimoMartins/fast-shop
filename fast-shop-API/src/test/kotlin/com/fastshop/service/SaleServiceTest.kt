package com.fastshop.service

import com.fastshop.domain.exception.UnsufficientMoneyException
import com.fastshop.domain.exception.UnsufficientProductException
import com.fastshop.domain.model.*
import com.fastshop.domain.service.SaleService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest
class SaleServiceTest {

    @Autowired
    lateinit var saleService: SaleService

    @Test
    fun `should throw an UnsufficientProductException when create a new sale`() {
        val newSale = Sale(
            amountPaid = BigDecimal("3000"),
            paymentMode = PaymentMode.MONEY,
            saleItens = mutableListOf(
                SaleItem(1, 2, product = Product(1)),
                SaleItem(5, 1, product = Product(5))
            )
        )

        Assertions.assertThrows(UnsufficientProductException::class.java) {
            saleService.createSale(newSale)
        }
    }

    @Test
    fun `Must throw a UnsufficientMoneyException when create a new sale`() { //TODO
        val newSale = Sale(
            amountPaid = BigDecimal("100"),
            paymentMode = PaymentMode.MONEY,
            saleItens = mutableListOf(
                SaleItem(quantity = 2, product = Product(1)),
            )
        )
        Assertions.assertThrows(UnsufficientMoneyException::class.java) {
            saleService.createSale(newSale)
        }
    }

    @Test
    fun `should create a new sale`() {
        val newSale = Sale(
            amountPaid = BigDecimal("5000"),
            paymentMode = PaymentMode.MONEY,
            saleItens = mutableListOf(
                SaleItem(quantity = 2, product = Product(1)),
                SaleItem(quantity = 1, product = Product(2))
            )
        )

        newSale.saleItens.forEach { it.sale = newSale }

        val savedSale = saleService.createSale(newSale)
        Assertions.assertNotNull(savedSale)
    }

    @Test
    fun `should  create a new sale`() { //TODO
        val newSale = Sale(
            amountPaid = BigDecimal("3000"),
            paymentMode = PaymentMode.MONEY,
            saleItens = mutableListOf(
                SaleItem(1, 2, product = Product(1)),
                SaleItem(5, 1, product = Product(5))
            )
        )
//        newSale.calculateTotal()
        Assertions.assertThrows(UnsufficientProductException::class.java) {
            saleService.createSale(newSale)
        }
    }

    @Test
    fun `should  return the cmplete list of sales`() {
        val result = saleService.allSales()
        Assertions.assertNotNull(result)
    }

}