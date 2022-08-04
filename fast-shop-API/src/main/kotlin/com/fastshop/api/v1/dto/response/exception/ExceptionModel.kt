package com.fastshop.api.v1.dto.response.exception

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

data class ExceptionModel(
    val status:Int,
    val timestamp:OffsetDateTime=OffsetDateTime.now(),
    val tittle:String,
    val details:String,
    @JsonProperty("field_errors")

    @field: JsonInclude(JsonInclude.Include.NON_NULL)
    val fiedErrors:List<CustomFieldError>?=null
)
data class CustomFieldError(
    @JsonProperty("field_name")
    val fieldName:String,
    @JsonProperty("error_message")
    val message:String
    )
