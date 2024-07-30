package Unad.telecom_fase5.entity.soat;

import Unad.telecom_fase5.entity.consultaVerifik.VehicleInfoAuxDTO;
import Unad.telecom_fase5.entity.consultaVerifik.VehicleInfoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@ToString
public class Soat {
    @Getter
    @Setter
    private VehicleInfoAuxDTO vehiculo;


    public Soat(VehicleInfoAuxDTO vehiculo) {
        this.vehiculo = vehiculo;
    }


    public byte[] generarSOAT() {
        try {
            // Fecha actual
            LocalDate fechaActual = LocalDate.now();

            // Fecha inicial: sumar un día a la fecha actual
            LocalDate fechaInicial = fechaActual.plusDays(1);

            // Fecha final: sumar un año a la fecha inicial
            LocalDate fechaFinal = fechaInicial.plusYears(1);

            // Formatear las fechas
            DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
            DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");


            List<VehicleInfoAuxDTO> vehiculos = Arrays.asList(getVehiculo());
            Map<String, Object> parameters = new HashMap();
            parameters.put("nonewsoat", getVehiculo().getNonewsoat());
            parameters.put("placa", getVehiculo().getPlaca());
            parameters.put("clase", getVehiculo().getClase());
            parameters.put("tipo", getVehiculo().getTipo());
            parameters.put("cilindraje", getVehiculo().getCilindraje());
            parameters.put("modelo", getVehiculo().getModelo());
            parameters.put("ocupantes", getVehiculo().getOcupantes());
            parameters.put("marca", getVehiculo().getMarca());
            parameters.put("linea", getVehiculo().getLinea());
            parameters.put("nomotor", getVehiculo().getNomotor());
            parameters.put("nochasis", getVehiculo().getNochasis());
            parameters.put("novin", getVehiculo().getNovin());
            parameters.put("toneladas", getVehiculo().getToneladas());
            parameters.put("nombres", getVehiculo().getNombres());
            parameters.put("telefono", getVehiculo().getTelefono());
            parameters.put("identificacion", getVehiculo().getIdentificacion());
            parameters.put("yyycomsoat", fechaInicial.format(formatterYear));
            parameters.put("mmcomsoat", fechaInicial.format(formatterMonth));
            parameters.put("ddcomsoat", fechaInicial.format(formatterDay));
            parameters.put("yyyvennusoat", fechaFinal.format(formatterYear));
            parameters.put("mmvennusoat", fechaFinal.format(formatterMonth));
            parameters.put("ddvennusoat", fechaFinal.format(formatterDay));
            parameters.put("codigotarifa", getVehiculo().getCodigotarifa());
            parameters.put("costototal", ((getVehiculo().getCostototal())));
            parameters.put("prima", ((getVehiculo().getPrima())));
            parameters.put("contribucion", ((getVehiculo().getContribucion())));
            parameters.put("runt", ((getVehiculo().getRunt())));

            System.out.println(getVehiculo().toString() + " AQUI TODO BIEN");
            InputStream inputStream = getClass().getResourceAsStream("/soatV2.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);


            System.out.println("DIFERENTE DE NULL");
            System.out.println("- PASO 1");

            System.out.println("- PASO 2");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vehiculos);
            System.out.println("- PASO 3");

            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            System.out.println("- PASO 4");

            System.out.println("Propiedades del JasperPrint:");
            for (String propertyName : print.getPropertyNames()) {
                System.out.println(propertyName + ": " + print.getProperty(propertyName));
            }

            return JasperExportManager.exportReportToPdf(print);

        } catch (Exception e) {
            System.out.println("ERROR"+e.getMessage());
            e.printStackTrace(); // Añadido para depuración
            throw new RuntimeException(e);
        }
    }
}