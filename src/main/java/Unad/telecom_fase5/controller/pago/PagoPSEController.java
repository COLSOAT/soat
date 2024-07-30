package Unad.telecom_fase5.controller.pago;

import Unad.telecom_fase5.entity.consultaVerifik.UserEntity;
import Unad.telecom_fase5.servicios.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.*;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.exceptions.MPApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Unad.telecom_fase5.entity.pago.PagoPseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/pago")
public class PagoPSEController {

    private final ObjectMapper objectMapper = new ObjectMapper(); // Para convertir a JSON

    @Autowired
    UserService userService;
    @PostMapping("r5soat-faf3abc78f43.herokuapp.com/pse")
    public ResponseEntity<String> pagosPSE(@RequestBody PagoPseDTO pagoPseDTO) {
        try {
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = fechaActual.format(formato);
            UserEntity userEntity= new UserEntity();
            userEntity.setDocumento(pagoPseDTO.getDocumento());
            userEntity.setFecha(fechaFormateada);
            userEntity.setInformacion("PASO 2: (PAGO), SE MOSTRARON DATOS DEL VEHICULO PARA EL PAGO");

            MercadoPagoConfig.setAccessToken("APP_USR-2625059277787645-041123-ea665332ba486bda3a192d3455a33696-1188679528");
            PaymentClient client = new PaymentClient();

            IdentificationRequest identification = IdentificationRequest.builder()
                    .type("CC")
                    .number(pagoPseDTO.getDocumento())
                    .build();

            PaymentPayerAddressRequest address = PaymentPayerAddressRequest.builder()
                    .zipCode("111111")
                    .streetName("Calle")
                    .streetNumber("11111")
                    .neighborhood("CENTER")
                    .city("Medellin")
                    .federalUnit("Antioquia")
                    .build();

            PaymentPayerPhoneRequest phone = PaymentPayerPhoneRequest.builder()
                    .areaCode("57")
                    .number(pagoPseDTO.getTelefono())
                    .build();

            PaymentPayerRequest payer = PaymentPayerRequest.builder()
                    .email(pagoPseDTO.getCorreo())
                    .entityType("individual")
                    .identification(identification)
                    .address(address)
                    .phone(phone)
                    .build();

            PaymentAdditionalInfoRequest additionalInfo = PaymentAdditionalInfoRequest.builder()
                    .ipAddress("127.0.0.1")
                    .build();

            PaymentTransactionDetailsRequest transactionDetails = PaymentTransactionDetailsRequest.builder()
                    .financialInstitution(pagoPseDTO.getMedioPagoID())
                    .build();

            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(new BigDecimal(pagoPseDTO.getValorPagoBigDecimal()))
                    .description("Product description")
                    .paymentMethodId("pse")
                    .additionalInfo(additionalInfo)
                    .transactionDetails(transactionDetails)
                    .notificationUrl("https://www.unad.edu.co")
                    .callbackUrl("https://www.unad.edu.co")
                    .payer(payer)
                    .build();

            Payment payment = client.create(paymentCreateRequest);

            // Convertir el objeto Payment a JSON
            String paymentJson = objectMapper.writeValueAsString(payment);
            userService.saveOrUpdateUser(userEntity);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(paymentJson);

        } catch (MPApiException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la API de MercadoPago: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error general: " + e.getMessage());
        }
    }
}