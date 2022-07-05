package com.fastshop.domain.service

import com.fastshop.domain.model.Sale

class SalesFlowService {
    fun createSale()=""
    fun confirmSale(sale: Sale)=sale.run {
        this.status
    }
    fun confirmSale( saleId:Long)=""
    fun cancelSale()=""
}
