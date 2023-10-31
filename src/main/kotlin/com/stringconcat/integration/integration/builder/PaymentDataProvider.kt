package com.stringconcat.integration.integration.builder

import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Component
class PaymentDataProvider {
    fun provide(number: String, expirationDate: OffsetDateTime, cvv: Int, amount: BigDecimal): String {
        var dataContainer = StringBuilder();

        dataContainer = dataContainer.append("card:${number};")
        dataContainer = dataContainer.append("cvv:${cvv};")
        dataContainer = dataContainer.append("expiration_date:${expirationDate};")
        dataContainer = dataContainer.append("amount:${amount};")
        dataContainer = dataContainer.append("payment_id:${UUID.randomUUID()}")

        return dataContainer.toString()
    }
}