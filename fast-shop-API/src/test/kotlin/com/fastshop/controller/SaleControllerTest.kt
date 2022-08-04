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
class SaleControllerTest {
    val  URI="http://localhost:8080/sales"

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should create a new sale`() {
        val saleJson="{ \"amount_paid\":890, \"client_type\": \"CONSUMER\", \"payment_mode\":\"POS\" ," +
                "\"itens\":[ {\"product_id\": 1,\"quantity\":3},{\"product_id\": 2,\"quantity\":1}]}"
        mockMvc.post(URI){
            contentType= MediaType.APPLICATION_JSON
            content=saleJson
        }.andDo { println() }.andExpect {
            status { isCreated() }
            content { string(
                Matchers.containsString("client_type") )}

        }
    }

    fun `should not create a new sale`() {

    }

    fun `should throw EntityNotFoundException a new sale`() {

    }
}