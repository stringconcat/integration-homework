package com.stringconcat.integration

import com.stringconcat.integration.dto.GatewayPayment
import com.stringconcat.integration.dto.Payment
import java.io.IOException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.retry.annotation.Retryable
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("payments")
class GatewayController(val restTemplate: RestTemplate) {

    private val URL = "/store"


    @PostMapping("/new")
    @Retryable(IOException::class)
    fun execute(@Validated @RequestBody payment: Payment): String {
        LOGGER.info("Incoming request: {}", payment)
        val result = restTemplate.postForEntity(URL,
            GatewayPayment(payment),
            String::class.java
        )

        return if (result.statusCode != HttpStatus.OK) {
            result.body!!
        } else {
            "Payment failed"
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(GatewayController::class.java)
    }
}