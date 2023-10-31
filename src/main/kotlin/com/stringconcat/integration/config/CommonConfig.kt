package com.stringconcat.integration.config

import com.stringconcat.integration.properties.GatewayProperty
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.retry.annotation.EnableRetry
import org.springframework.web.client.RestTemplate
import java.net.InetSocketAddress
import java.net.Proxy

@Configuration
@EnableRetry
class CommonConfig {

    @Bean
    fun gatewayRestTemplate(gatewayProperty: GatewayProperty): RestTemplate {
        LOGGER.info("Setting up gateway with HOSTNAME => ${gatewayProperty.host} and PORT => ${gatewayProperty.port}")

        val requestFactory = SimpleClientHttpRequestFactory()
        val gateway = Proxy(Proxy.Type.HTTP, InetSocketAddress(gatewayProperty.host, gatewayProperty.port))
        requestFactory.setProxy(gateway)

        return RestTemplate(requestFactory)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CommonConfig::class.java)
    }
}