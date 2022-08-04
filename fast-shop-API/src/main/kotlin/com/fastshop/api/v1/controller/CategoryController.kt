package com.fastshop.api.v1.controller

import com.fastshop.api.v1.dto.input.CategoryInput
import com.fastshop.api.v1.dto.response.CategoryModel
import com.fastshop.api.v1.dto.toCategorieCollectionModel
import com.fastshop.api.v1.dto.toCategory
import com.fastshop.api.v1.dto.toCategoryModel
import com.fastshop.domain.repository.CategoryRepository
import com.fastshop.domain.service.CategoryService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.hateoas.CollectionModel
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
    @Cacheable(value = ["categorie"])
     fun getAllCategories(@PageableDefault(size = 5, page = 0) pageable: Pageable
    ): CollectionModel<CategoryModel> {

        return categoryRepository.findAll(pageable).let {
          if( it.isEmpty ) toCategorieCollectionModel(mutableListOf())
            else  toCategorieCollectionModel(it.get().toList())
        }
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int) = toCategoryModel(categoryService.findCategoryByIdOrFail(id))
}
