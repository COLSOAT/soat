package Unad.telecom_fase5.controller.pago;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.paymentmethod.PaymentMethodClient;
import com.mercadopago.resources.paymentmethod.PaymentMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/pagos") // Ruta base
public class MediosDepagoController {

    @PostMapping("/medios")
    public ResponseEntity<List> mediosPagos() {
        List listMedios = null;
        try {
            MercadoPagoConfig.setAccessToken("APP_USR-910859472954590-080210-00c6984d4f718add7cce9bdc88abe143-280720499");
            PaymentMethodClient paymentMethodClient = new PaymentMethodClient();
            for (PaymentMethod result : paymentMethodClient.list().getResults()) {
                if (result.getId().equals("pse")) {
                    listMedios = result.getFinancialInstitutions();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo de errores
        }
        return ResponseEntity.ok(listMedios);
    }
}
