package com.stringconcat.integration.integration.exception

class GatewayIntegrationException(override val message: String, val code: Int) : RuntimeException(message) {
}