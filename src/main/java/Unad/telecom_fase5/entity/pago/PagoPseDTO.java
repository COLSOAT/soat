package Unad.telecom_fase5.entity.pago;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class PagoPseDTO {
    private String documento;
    private String telefono;
    private String correo;
    private String medioPagoID;
    private String valorPagoBigDecimal;

}
