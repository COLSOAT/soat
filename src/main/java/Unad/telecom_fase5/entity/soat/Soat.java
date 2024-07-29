package Unad.telecom_fase5.entity.soat;

import Unad.telecom_fase5.entity.consultaVerifik.VehicleInfoAuxDTO;
import Unad.telecom_fase5.entity.consultaVerifik.VehicleInfoDTO;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


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


            InputStream is = new FileInputStream("src/main/resources/static/soatV2.jrxml");
            JasperReport report = JasperCompileManager.compileReport(is);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vehiculos);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
            return JasperExportManager.exportReportToPdf(print);

        } catch (JRException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
