package com.stringconcat.integration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "gateway.server")
data class GatewayProperty(var host: String = "", var port: Int = -1, var storeEndpoint: String = "")