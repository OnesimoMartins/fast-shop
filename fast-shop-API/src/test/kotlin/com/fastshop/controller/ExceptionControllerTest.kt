package com.fastshop.controller

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post


@SpringBootTest
@AutoConfigureMockMvc
internal class ExceptionControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    val URI="http://localhost:8080/categories"

    @Test
    fun `should throw a handleHttpMediaTypeNotSupported given a MediaType APPLICATION_XML `() {
        val categoryInput = "{ \"name\":\"higiene\" }"
        mockMvc.post(URI) {
            contentType = MediaType.APPLICATION_XML
            content = categoryInput

        }.andDo { println() }.andExpect {
            status { isUnsupportedMediaType() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                string(
                    Matchers.containsString("\" the unique media type supported by this api is 'application/json' \"")
                )
            }
        }
    }


}