package com.stringconcat.integration.api.dto

import java.math.BigDecimal
import java.time.OffsetDateTime

data class PaymentRequest(
        val number: String,
        val expirationDate: OffsetDateTime,
        val owner: String,
        val cvv: Int,
        val amount: BigDecimal
)