package com.fastshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@CrossOrigin
@Configuration
@EnableCaching
@SpringBootApplication
class FastShopApplication

fun main(args: Array<String>) {
    runApplication<FastShopApplication>(*args)
}


