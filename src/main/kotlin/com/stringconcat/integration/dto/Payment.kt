package com.stringconcat.integration.dto

import java.math.BigDecimal
import java.time.OffsetDateTime

data class Payment(
    val number: String,
    val date: OffsetDateTime,
    val owner: String,
    val cvv: Int,
    val amount: BigDecimal
)