package com.fastshop.api.v1.dto.response

import lombok.Builder
import org.springframework.hateoas.RepresentationModel

@Builder
data class CategoryModel(val id:Int,val name:String): RepresentationModel<CategoryModel>()