package com.fastshop.api.dto.input

import com.fastshop.domain.model.Category

data class CategoyInput(val name:String) {
    fun toCategory()=Category(name=name)
}
