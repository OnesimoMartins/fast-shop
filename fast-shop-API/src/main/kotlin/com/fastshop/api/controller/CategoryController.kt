package com.fastshop.api.controller

import com.fastshop.api.dto.input.CategoyInput
import com.fastshop.domain.model.Category
import com.fastshop.domain.repository.CategoryRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(private val categoryRepository: CategoryRepository) {

    @PostMapping
     fun createCategory( categoyInput: CategoyInput)
             =categoryRepository.save(categoyInput.toCategory())

    @GetMapping
//    @Cacheable
    fun getAllCategories(): MutableList<Category> =categoryRepository.findAll()
}