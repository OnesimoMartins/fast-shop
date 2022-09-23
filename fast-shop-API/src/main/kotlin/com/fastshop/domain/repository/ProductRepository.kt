package com.fastshop.domain.repository

import com.fastshop.domain.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository:JpaRepository<Product,Long>{

    @Query("from Product p where p.availableUnities >0")
    fun findAvailableProducts(pageable: Pageable): Page<Product>

}