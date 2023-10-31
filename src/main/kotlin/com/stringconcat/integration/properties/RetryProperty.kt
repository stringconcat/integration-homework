package com.stringconcat.integration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "retry")
data class RetryProperty(var maxAttempts: Int = -1, var maxDelay: Int = -1)