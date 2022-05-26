package com.stringconcat.integration.dto

class GatewayPayment(payment: Payment) {
    val data = "${payment.number}:${payment.date}:${payment.cvv}:${payment.owner}:${payment.amount}"
}
