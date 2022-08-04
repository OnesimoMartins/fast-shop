package com.fastshop.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should return 200 in status when request all categories`() {
        mockMvc.get("http://localhost:8080/categories"){
            contentType= MediaType.APPLICATION_JSON
        }.andDo { print() }
            .andExpect {
            content { contentType(MediaType.APPLICATION_JSON) }
                status { isOk() }

            }
    }

    @Test
    fun `should return status 400 given a blank name`() {
        mockMvc.post("http://localhost:8080/categories") {
            contentType = MediaType.APPLICATION_JSON
            content = "{ \"name\" :\" \"}"
        }.andDo { println() }
            .andExpect { status { isBadRequest() } }
    }
}