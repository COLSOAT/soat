package com.mercadopago;

import Unad.telecom_fase5.UTILS;
import com.mercadopago.client.cardtoken.CardTokenClient;
import com.mercadopago.client.cardtoken.CardTokenRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.CardToken;
import com.mercadopago.resources.payment.Payment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TarjetaCreditDEbit {
    public static void main(String[] args) {
        try {
            String ACCESS_TOKEN = UTILS.getTokenMercado();

            MercadoPagoConfig.setAccessToken(ACCESS_TOKEN);

            // Crear una instancia del cliente de tokens de tarjeta
            CardTokenClient cardTokenClient = new CardTokenClient();

            // Crear un nuevo token de tarjeta con los datos proporcionados
            CardTokenRequest request = CardTokenRequest.builder()
                    .cardNumber("5187617195286526") // Número de tarjeta Visa de débito
                    .expirationMonth(8) // Mes de expiración
                    .expirationYear(2025) // Año de expiración
                    .securityCode("145") // Código de seguridad
                    .cardholderName("Oscar Carrillo") // Nombre del titular de la tarjeta
                    .build();

            CardToken cardToken = cardTokenClient.create(request);

            // Verificar si el token de tarjeta es válido
            if (cardToken == null || cardToken.getId() == null) {
                System.err.println("Card token creation failed.");
                return;
            }

            System.out.println("Card Token: " + cardToken.getId());

            // Crear encabezados personalizados
            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key", "<SOME_UNIQUE_VALUE>");

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders(customHeaders)
                    .build();

            // Crear una instancia del cliente de pagos
            PaymentClient paymentClient = new PaymentClient();

            // Crear una solicitud de pago
            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(new BigDecimal("10000"))
                            .token(cardToken.getId())
                            .description("Payment description")
                            .installments(1)
                            .paymentMethodId("master")
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email("carrillozuletaoscar@gmail.com")
                                            .firstName("Oscar")
                                            .lastName("Carrillo")
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type("CC")
                                                            .number("1073995282")
                                                            .build())
                                            .build())
                            .build();

            // Crear el pago y obtener la respuesta
            Payment payment = paymentClient.create(paymentCreateRequest, requestOptions);
            System.out.println("Payment ID: " + payment.getId());
            System.out.println("Payment Status: " + payment.getStatus());
            System.out.println("Payment Status Detail: " + payment.getStatusDetail());
            System.out.println("Payment Description: " + payment.getDescription());
            System.out.println(payment.getTransactionDetails());

        } catch (MPApiException e) {
            // Obtener detalles adicionales de la respuesta
            System.err.println("API Error: " + e.getMessage());
            System.out.println(e.getApiResponse().getContent());
            e.printStackTrace();
        } catch (MPException e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
