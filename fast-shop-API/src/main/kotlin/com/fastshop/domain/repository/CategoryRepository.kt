package com.fastshop.domain.repository

import com.fastshop.domain.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository:JpaRepository<Category,Long>