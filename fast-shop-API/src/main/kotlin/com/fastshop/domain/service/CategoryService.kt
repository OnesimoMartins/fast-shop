package com.fastshop.domain.service

import com.fastshop.domain.repository.CategoryRepository
import com.fastshop.domain.model.Category
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class CategoryService(val categoryRepository: CategoryRepository) {

   fun findCategoryByIdOrFail(id:Int): Category = categoryRepository.findById(id)
    .orElseThrow { EntityNotFoundException("No Category with id $id was found") }

    fun updateCategory(id: Int,newCategory:Category):Category=
        this.categoryRepository.save(
            newCategory.let {
                val category=findCategoryByIdOrFail(id)
                category.name=newCategory.name
                return@let categoryRepository.save(category)
            })

    fun deleteCategoryById(id: Int)=
       categoryRepository.delete( this.findCategoryByIdOrFail(id))


}