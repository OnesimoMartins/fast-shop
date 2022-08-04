package com.fastshop.domain.service

import com.fastshop.core.utils.isPositive
import com.fastshop.core.utils.lessThan
import com.fastshop.domain.exception.*
import com.fastshop.domain.model.Sale
import com.fastshop.domain.model.SaleItem
import com.fastshop.domain.repository.SaleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.EntityManager
import javax.persistence.EntityNotFoundException

@Service
class SaleService(
    private val saleRepository: SaleRepository,
    private val productService: ProductService,
) {
    fun createSale(sale: Sale) = with(sale) {
        this.date= LocalDateTime.now()
        // validating the products

        this.saleItens.forEach { item ->
            //verifing if the product exists
            val product = productService.findProductById(item.product.id!!)
            item.product = product
            item.calculateTotal()// calculating total per each item
            item.sale=this

            //verifing if it has sufficient stock
            if (product.availableUnities!! lessThan  item.quantity)
                throw UnsufficientProductException(product.availableUnities!!, item.quantity)
        }

        //calculating the total amount
        this.calculateTotal()

            return@with saleRepository.save(this)
    }


    @Transactional
    fun confirmSale(id:Long,givenMoney:BigDecimal):Sale{
        val sale=this.findSaleByIdOrFail(id)
             sale.confirmSale(givenMoney)
            return saleRepository.save(sale)
    }

    @Transactional
    fun cancelSale(id: Long){
        this.findSaleByIdOrFail(id).also {
            it.cancelSale()
            saleRepository.save(it)
        }
    }

    fun allSales(): MutableList<Sale> = this.saleRepository.findAll()

    fun findSaleByIdOrFail(id:Long): Sale =this.saleRepository.findById(id)
         .orElseThrow{ EntityNotFoundException("No Sale with id $id was found")}

    @Transactional
    fun updateItemQunatity(saleId: Long, itemId: Long, quantity: Int): Sale {
       val sale= findSaleByIdOrFail(saleId)

        if(sale.isConfirmed()) throw  SaleAlreadyConfirmedException(sale.id!!,sale.date!!)

        if(sale.isCanceled()) throw SaleAlreadyCanceledException(sale.id!!,sale.date!!)

        val  item=sale.setItemQuantity(itemId,quantity)

          //verifing if it has sufficient stock
        if(item.product.availableUnities!! lessThan quantity)
              throw UnsufficientProductException(item.product.availableUnities!!, quantity)

        sale.calculateTotal()

        return saleRepository.save(sale)
    }

    @Transactional
    fun deleteSaleItem(saleId:Long,itemId:Long): Sale {

       if(saleRepository.saleHasItem(saleId,itemId)){
           saleRepository.deleteSaleItem(saleId,itemId)
              val sale= this.findSaleByIdOrFail(saleId)
                 sale.calculateTotal()
            return sale
           }
        else throw BusinessException("The sale '$saleId' doesn´t exists or doesn´t have sale item '$itemId' ")
    }
}