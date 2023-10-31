package com.stringconcat.integration.service.exception

class PaymentProcessingException(override val message: String, val code: Int) : RuntimeException(message) {
}