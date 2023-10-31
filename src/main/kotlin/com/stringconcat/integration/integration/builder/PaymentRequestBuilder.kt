package com.stringconcat.integration.integration.builder

import com.stringconcat.integration.integration.dto.GatewayPaymentRequest
import org.springframework.stereotype.Component

@Component
class PaymentRequestBuilder {
    fun provide(data: String): GatewayPaymentRequest = GatewayPaymentRequest(data)
}