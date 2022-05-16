package com.stringconcat.integration

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class AppController(val restTemplate: RestTemplate) {

    private val URL = "/data"

    @GetMapping("/")
    fun execute() {
        restTemplate.getForEntity(
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + URL,
                String::class.java
        )
    }

    @PostMapping("/")
    fun execute(@RequestBody request: String?) {
        restTemplate.postForEntity(
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + URL,
                request,
                String::class.java
        )
    }
}