package Unad.telecom_fase5.servicios.consultaVerifik;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PasaDeTarifaSaldo {
    int discountPercentage = 20;
    Map<Integer, Double> dataMap = new HashMap<>();

    public PasaDeTarifaSaldo() {
        dataMap.put(100, 111600.0);
        dataMap.put(110, 230200.0);
        dataMap.put(120, 308500.0);
        dataMap.put(130, 830100.0);
        dataMap.put(140, 347700.0);
        dataMap.put(150, 347700.0);
        dataMap.put(211, 864500.0);
        dataMap.put(212, 1039300.0);
        dataMap.put(221, 1032300.0);
        dataMap.put(222, 1222900.0);
        dataMap.put(231, 1210900.0);
        dataMap.put(232, 1389500.0);
        dataMap.put(310, 968800.0);
        dataMap.put(320, 1398900.0);
        dataMap.put(330, 1768900.0);
        dataMap.put(410, 1090100.0);
        dataMap.put(420, 1374300.0);
        dataMap.put(430, 1647600.0);
        dataMap.put(511, 487500.0);
        dataMap.put(512, 646500.0);
        dataMap.put(521, 593800.0);
        dataMap.put(522, 738600.0);
        dataMap.put(531, 693500.0);
        dataMap.put(532, 822500.0);
        dataMap.put(611, 869500.0);
        dataMap.put(612, 1109800.0);
        dataMap.put(621, 1163900.0);
        dataMap.put(622, 1397700.0);
        dataMap.put(711, 253300.0);
        dataMap.put(712, 316200.0);
        dataMap.put(721, 314600.0);
        dataMap.put(722, 388600.0);
        dataMap.put(731, 405600.0);
        dataMap.put(732, 475700.0);
        dataMap.put(810, 605000.0);
        dataMap.put(910, 598200.0);
        dataMap.put(920, 867500.0);

    }

    public String getValueInCOP(Integer key) {
        // Verificar si el mapa contiene la clave
        if (dataMap.containsKey(key)) {
            // Obtener el valor asociado a la clave
            Double value = dataMap.get(key);

            // Aplicar el descuento
            double discountedValue = value - (value * discountPercentage / 100);

            // Redondear el valor al entero más cercano
            long roundedValue = Math.round(discountedValue);

            // Crear un formateador para la moneda colombiana
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
            // Configurar el formateador para que no muestre los centavos
            currencyFormat.setMinimumFractionDigits(0);
            currencyFormat.setMaximumFractionDigits(0);
            // Formatear el valor como moneda colombiana
            return currencyFormat.format(roundedValue);
        } else {
            // Retornar un mensaje si la clave no se encuentra
            return "Clave no encontrada.";
        }
    }

}
