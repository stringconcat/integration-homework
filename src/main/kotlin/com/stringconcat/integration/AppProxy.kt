package com.stringconcat.integration

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.net.InetSocketAddress
import java.net.Proxy

@Configuration
class AppProxy(@Value("\${proxy-server-host}") val host: String,
               @Value("\${proxy-server-port}") val port: String) {

    @Bean
    fun restTemplate(): RestTemplate {
        LOGGER.info("Setting up proxy with HOSTNAME => $host and PORT => $port")
        val requestFactory = SimpleClientHttpRequestFactory()
        val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(host, port.toInt()))
        requestFactory.setProxy(proxy)
        return RestTemplate(requestFactory)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AppProxy::class.java)
    }
}