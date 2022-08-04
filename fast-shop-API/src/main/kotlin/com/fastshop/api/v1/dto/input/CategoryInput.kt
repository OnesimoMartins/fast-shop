package com.fastshop.api.v1.dto.input

import com.fastshop.core.validation.NameMustNotExistsInDb
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CategoryInput(

  @field: NotBlank(message = "The name of category cannot be blank ")
  @field:Size(min = 3, message = "Name too short for a category")
  @field:NameMustNotExistsInDb(message = "This name is already registered on our database")
  val name:String

  )
