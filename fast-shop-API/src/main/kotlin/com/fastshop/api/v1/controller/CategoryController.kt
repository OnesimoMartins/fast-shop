package com.fastshop.api.v1.controller

import com.fastshop.api.v1.dto.input.CategoryInput
import com.fastshop.api.v1.dto.response.CategoryModel
import com.fastshop.api.v1.dto.toCategory
import com.fastshop.api.v1.dto.toCategoryModel
import com.fastshop.api.v1.dto.toCategoryPagedModel
import com.fastshop.domain.repository.CategoryRepository
import com.fastshop.domain.service.CategoryService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.hateoas.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin
@RestController
@RequestMapping("/categories", consumes = ["application/json"], produces = ["application/json"])
class CategoryController(
    private val categoryRepository: CategoryRepository,
    private val categoryService: CategoryService
){

    @PostMapping
    @CacheEvict(value =["categorie"] )
    @ResponseStatus(HttpStatus.CREATED)
     fun createCategory(@RequestBody @Valid categoyInput: CategoryInput):CategoryModel {
    var category=toCategory(categoyInput)
        category=categoryRepository.save( category)
        return toCategoryModel(category)
    }

    @GetMapping
     fun getAllCategories(@PageableDefault(size = 5, page = 0) pageable: Pageable
    ): PagedModel<CategoryModel> {

        return toCategoryPagedModel(
        categoryRepository.findAll(pageable)
        )
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int) = toCategoryModel(categoryService.findCategoryByIdOrFail(id))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(@PathVariable id: Int) = categoryService.deleteCategoryById(id)


    @PutMapping("/{id}")
    @CacheEvict(value =["categorie"] )
    fun updateCategory(@PathVariable id: Int,
                       @RequestBody @Valid categoyInput: CategoryInput) = toCategoryModel(
         categoyInput.let { return@let categoryService.updateCategory(id,toCategory(it)) }
    )

}