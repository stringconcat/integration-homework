package com.stringconcat.integration.service.action

import com.stringconcat.integration.service.data.OperationStatus
import java.math.BigDecimal
import java.time.OffsetDateTime

interface MakeStoreAction {
    fun store(number: String, expirationDate: OffsetDateTime, owner: String, cvv: Int, amount: BigDecimal): OperationStatus
}