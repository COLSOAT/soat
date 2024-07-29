package com.mercadopago;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.*;
import com.mercadopago.client.paymentmethod.PaymentMethodClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.paymentmethod.PaymentMethod;
import com.mercadopago.resources.paymentmethod.PaymentMethodFinancialInstitutions;
import org.hibernate.mapping.List;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PSE {
    public static void main(String[] args) {
        try {
            MercadoPagoConfig.setAccessToken("APP_USR-2625059277787645-041123-ea665332ba486bda3a192d3455a33696-1188679528");
            Long sNeki = null;
            PaymentMethodClient paymentMethodClient = new PaymentMethodClient();
            for (PaymentMethod result : paymentMethodClient.list().getResults()) {
                if (result.getId().equals("pse")) {
                    for (PaymentMethodFinancialInstitutions financialInstitution : result.getFinancialInstitutions()) {
                        if (financialInstitution.getDescription().equals("Nequi")) {
                            sNeki = financialInstitution.getId();
                        }
                    }
                }
            }
            PaymentClient client = new PaymentClient();
            IdentificationRequest identification =
                    IdentificationRequest.builder()
                            .type("CC")
                            .number("1073995282")
                            .build();
            PaymentPayerAddressRequest address =
                    PaymentPayerAddressRequest.builder()
                            .zipCode("111111")
                            .streetName("Calle 54")
                            .streetNumber("#11a-30")
                            .neighborhood("Molinos")
                            .city("Bogota")
                            .federalUnit("Cundinamarca")
                            .build();
            PaymentPayerPhoneRequest phone =
                    PaymentPayerPhoneRequest.builder()
                            .areaCode("57")
                            .number("3135331533")
                            .build();
            PaymentPayerRequest payer =
                    PaymentPayerRequest.builder()
                            .email("Carrillozuletaoscar@gmail.com")
                            .entityType("individual")
                            .identification(identification)
                            .address(address)
                            .phone(phone)
                            .build();
            PaymentAdditionalInfoRequest additionalInfo =
                    PaymentAdditionalInfoRequest.builder()
                            .ipAddress("127.0.0.1")
                            .build();
            PaymentTransactionDetailsRequest transactionDetails = PaymentTransactionDetailsRequest.builder()
                    .financialInstitution(String.valueOf(sNeki))
                    .build();
            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(new BigDecimal(10000))
                    .description("Product description")
                    .paymentMethodId("pse")
                    .additionalInfo(additionalInfo)
                    .transactionDetails(transactionDetails)
                    .notificationUrl("https://www.unad.edu.co")
                    .callbackUrl("https://www.unad.edu.co")
                    .payer(payer)
                    .build();
            client.create(paymentCreateRequest);
            Payment payment = client.create(paymentCreateRequest);
            System.out.println(payment);
        } catch (MPApiException e) {
            e.printStackTrace();
        } catch (MPException e) {
            e.printStackTrace();
        }
    }
}
