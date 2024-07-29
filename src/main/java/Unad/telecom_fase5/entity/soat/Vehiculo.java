package Unad.telecom_fase5.entity.soat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "vehiculo")
@ToString
@EqualsAndHashCode
public class Vehiculo {

    @Id
    @Getter
    @Setter
    @Column(name = "placa")
    String placa = "NO"; //EDT345

    @Getter
    @Setter
    @Column(name = "identificacion")
    private Long identificacion = 0L;

    @Getter
    @Setter
    @Nullable
    @Column(name = "nombres")
    private String nombres = "ERROR AL CONSULTAR DATA RUT";

    @Getter
    @Setter
    @Column(name = "telefono")
    private String telefono = "0000000000";


    @Getter
    @Setter
    @Column(name = "tipo")
    String tipo = "NO";//Particular

    @Getter
    @Setter
    @Column(name = "clase")
    String clase = "NO";//AUTOMOVIL


    @Getter
    @Setter
    @Column(name = "marca")
    String marca = "NO";//RENAULT


    @Getter
    @Setter
    @Column(name = "modelo")
    int modelo = 0000;//2007


    @Getter
    @Setter
    @Column(name = "linea")
    String linea = "NO";//R-CLIO II AUTHENTIQUE EO


    @Getter
    @Setter
    @Column(name = "cilindraje")
    int cilindraje = 0000;//1400


    @Getter
    @Setter
    @Column(name = "codigotarifa")
    String codigotarifa = "NO";//ROJO AMBAR

    @Getter
    @Setter
    @Column(name = "novin")
    String novin = "NO";

    @Getter
    @Setter
    @Column(name = "nomotor")
    String nomotor = "NO";

    @Getter
    @Setter
    @Column(name = "nochasis")
    String nochasis = "NO";

    //________________________________________________
    @Getter
    @Setter
    @Column(name = "nonewsoat")
    String nonewsoat = "NO";

    //________________________________________________
    @Getter
    @Setter
    @Column(name = "yyycomsoat")
    String yyycomsoat = "NO";

    @Getter
    @Setter
    @Column(name = "mmcomsoat")
    String mmcomsoat = "NO";

    @Getter
    @Setter
    @Column(name = "ddcomsoat")
    String ddcomsoat = "NO";

    @Getter
    @Setter
    @Column(name = "yyyvennusoat")
    String yyyvennusoat = "NO";

    @Getter
    @Setter
    @Column(name = "mmvennusoat")
    String mmvennusoat = "NO";

    @Getter
    @Setter
    @Column(name = "ddvennusoat")
    String ddvennusoat = "NO";

    @Getter
    @Setter
    @Column(name = "costototal")
    String costototal = "NO";

    @Getter
    @Setter
    @Column(name = "prima")
    String prima = "NO";

    @Getter
    @Setter
    @Column(name = "contribucion")
    String contribucion = "NO";


    @Getter
    @Setter
    @Column(name = "runt")
    String runt = "NO";


    //________________________________________________
    @Getter
    @Setter
    @Column(name = "ocupantes")
    int ocupantes = 0;

    @Getter
    @Setter
    @Column(name = "toneladas")
    double toneladas = 0000;


    @Getter
    @Setter
    @Column(name = "compro")
    String compro = "NO";

    @Getter
    @Setter
    @Column(name = "cobro")
    String cobro = "NO";


}
