package com.fastshop.api.v1.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/measurement-unities", consumes = ["application/json"], produces = ["application/json"])
class MeasurementUnitiesController {

    @GetMapping
    fun getUnities() = listOf(
        Measurement("cm", "Centímetro"),
        Measurement("m", "Metro"),
        Measurement("ml", "Melilitro"),
        Measurement("l", "Litro"),
        Measurement("kg", "Kilograma"),
        Measurement("g", "Grama"),
        Measurement("Outro", "Outro")
    )
}

data class Measurement(val id: String, val name: String)