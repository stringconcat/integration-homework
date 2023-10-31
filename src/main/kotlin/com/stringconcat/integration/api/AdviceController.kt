package com.stringconcat.integration.api

import com.stringconcat.integration.api.dto.ErrorResponse
import com.stringconcat.integration.integration.exception.GatewayIntegrationException
import com.stringconcat.integration.service.GatewayService
import com.stringconcat.integration.service.exception.PaymentProcessingException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@ControllerAdvice
class AdviceController(val gatewayService: GatewayService) {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse>? {
        LOGGER.error("Unknown exception. ${e.message}", e)

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse(-1, "Internal error"))
    }

    @ExceptionHandler(PaymentProcessingException::class)
    fun handleException(e: PaymentProcessingException): ResponseEntity<ErrorResponse>? {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse(e.code, e.message))
    }

    @ExceptionHandler(GatewayIntegrationException::class)
    fun handleException(e: GatewayIntegrationException): ResponseEntity<ErrorResponse>? {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse(e.code, e.message))
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AdviceController::class.java)
    }
}