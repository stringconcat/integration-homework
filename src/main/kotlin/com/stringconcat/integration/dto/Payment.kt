package com.stringconcat.integration.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import java.time.OffsetDateTime
import java.util.UUID

data class CreditCard(
        @field:NotBlank(message = "{validation.field.number.empty}")
        @field:Pattern(
                regexp = "^\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}$",
                message = "{validation.field.number.invalid-format}"
        )
        val number: String,
        @field:NotNull(message = "{validation.field.date.empty}")
        val date: OffsetDateTime,
        @field:NotBlank(message = "{validation.field.owner.empty}")
        @field:Pattern(
                regexp = "^[A-Z]+\\s[A-Z]+$",
                message = "{validation.field.owner.invalid-format}"
        )
        val owner: String,
        @field:NotNull(message = "{validation.field.cvv.empty}")
        @field:Pattern(
                regexp = "^[0-9]{3}$",
                message = "{validation.field.cvv.invalid-format}"
        )
        val cvv: Int?
)

data class Payment(
        val card: CreditCard,
        val date: OffsetDateTime,
        val id: UUID
)