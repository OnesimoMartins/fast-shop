package com.fastshop.controller


import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.math.BigDecimal



@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    val  URI="http://localhost:8080/products"

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should create a new product given a product input`() {
        val productInput="{ \"purchise_price\":420, \"reseller_price\": 420, \"price\":500 ," +
                "\"measurement_unit\":\"Kg\", \"name\": \"arroz joão\" , \"category_id\":1 }"

        mockMvc.post(URI){
            contentType= MediaType.APPLICATION_JSON
            content=productInput

        }.andDo { println() }.andExpect {
            status { isCreated() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                json(productInput,true)
            }//TODO
        }
    }


}