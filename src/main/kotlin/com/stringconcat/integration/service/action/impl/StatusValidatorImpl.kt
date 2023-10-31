package com.stringconcat.integration.service.action.impl

import com.stringconcat.integration.service.action.StatusValidator
import com.stringconcat.integration.service.data.OperationStatus
import com.stringconcat.integration.service.exception.PaymentProcessingException
import org.springframework.stereotype.Component

@Component
class StatusValidatorImpl : StatusValidator {
    override fun validate(status: OperationStatus) {
        if (status.code != 0) {
            throw PaymentProcessingException("Payment operation is failed with code '${status.code}': ${status.message}", status.code)
        }
    }
}