package com.stringconcat.integration.service

import com.stringconcat.integration.service.action.MakeStoreAction
import com.stringconcat.integration.service.action.StatusValidator
import com.stringconcat.integration.service.data.OperationStatus
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.OffsetDateTime

@Service
class GatewayService(
        var makeStoreAction: MakeStoreAction,
        var statusValidator: StatusValidator
) {
    fun store(number: String, expirationDate: OffsetDateTime, owner: String, cvv: Int, amount: BigDecimal): OperationStatus {
        return makeStoreAction.store(number, expirationDate, owner, cvv, amount)
                .let {
                    statusValidator.validate(it)
                    it
                }
    }
}