package com.stringconcat.integration.api

import com.stringconcat.integration.api.dto.PaymentRequest
import com.stringconcat.integration.api.dto.PaymentResponse
import com.stringconcat.integration.service.GatewayService
import org.slf4j.LoggerFactory
import org.springframework.retry.annotation.Retryable
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

@RestController
@RequestMapping("/api/v1/payment")
class GatewayController(val gatewayService: GatewayService) {

    @PostMapping("/new")
    fun execute(@Validated @RequestBody paymentRequest: PaymentRequest): PaymentResponse {
        LOGGER.info("Payment request for ${paymentRequest.owner}")

        val status = gatewayService.store(
                paymentRequest.number,
                paymentRequest.expirationDate,
                paymentRequest.owner,
                paymentRequest.cvv,
                paymentRequest.amount
        )

        return PaymentResponse(status.code)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(GatewayController::class.java)
    }
}