package com.fastshop.domain.repository

import com.fastshop.domain.model.Sale
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SaleRepository : JpaRepository<Sale, Long> {

    @Modifying
    @Query(value = "delete from sale_item where id=:saleItemId and sale_id=:saleId", nativeQuery = true)
    fun deleteSaleItem(@Param("saleId") saleId: Long, @Param("saleItemId") saleItemId: Long)

    @Query(
        value = "select ifnull(( select 'true' from sale_item where id=:saleItemId " +
                "and sale_id=:saleId),'false')"
        ,nativeQuery = true
    )
    fun saleHasItem(@Param("saleId") saleId: Long, @Param("saleItemId") saleItemId: Long): Boolean

}