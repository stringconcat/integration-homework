package com.stringconcat.integration.integration.dto

import com.fasterxml.jackson.annotation.JsonProperty

class GatewayPaymentRequest(payment: String) {
    @field:JsonProperty
    val data = payment
}
