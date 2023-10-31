package com.stringconcat.integration.integration

import com.stringconcat.integration.integration.builder.PaymentDataProvider
import com.stringconcat.integration.integration.builder.PaymentRequestBuilder
import com.stringconcat.integration.integration.dto.GatewayPaymentResponse
import com.stringconcat.integration.integration.exception.GatewayIntegrationException
import com.stringconcat.integration.properties.GatewayProperty
import com.stringconcat.integration.service.action.MakeStoreAction
import com.stringconcat.integration.service.data.OperationStatus
import org.slf4j.LoggerFactory
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.math.BigDecimal
import java.time.OffsetDateTime

@Component
class GatewayApi(
        var paymentDataProvider: PaymentDataProvider,
        var paymentRequestBuilder: PaymentRequestBuilder,
        var gatewayProperty: GatewayProperty,
        var restTemplate: RestTemplate
) : MakeStoreAction {

    @Retryable(IOException::class)
    override fun store(number: String, expirationDate: OffsetDateTime, owner: String, cvv: Int, amount: BigDecimal): OperationStatus {
        val paymentData = paymentDataProvider.provide(number, expirationDate, cvv, amount)
        val payload = paymentRequestBuilder.provide(paymentData);

        val result = restTemplate.postForEntity(gatewayProperty.storeEndpoint, payload, GatewayPaymentResponse::class.java)

        if (result.statusCode.isError) {
            logger.info("Integration is failed with response '${result.statusCode}' '${result.body}'")
            val message = "External system is not available. Message: '${result.body.message}'. Code: '${result.body.code}'"

            throw GatewayIntegrationException(message, result.body.code)
        }

        return OperationStatus(result.body.code, "OK")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GatewayApi::class.java)
    }
}