package com.stringconcat.integration

import com.stringconcat.integration.dto.CreditCard
import com.stringconcat.integration.dto.Payment
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.OffsetDateTime
import java.util.*

@RestController
@RequestMapping("payments")
class AppGateway(val restTemplate: RestTemplate) {

    private val URL = "/store"

    @GetMapping("/last")
    fun execute(): ResponseEntity<Payment> {
        return restTemplate.getForEntity(
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + URL,
                Payment::class.java,
                mapOf("id" to -1)
        )
    }

    @PostMapping("/new")
    fun execute(@Validated @RequestBody card: CreditCard): ResponseEntity<Payment> {
        return restTemplate.postForEntity(
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + URL,
                card,
                Payment::class.java,
                OffsetDateTime.now(),
                UUID.randomUUID()
        )
    }
}