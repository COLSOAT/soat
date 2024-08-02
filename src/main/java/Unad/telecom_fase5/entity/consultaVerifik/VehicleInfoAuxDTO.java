package Unad.telecom_fase5.entity.consultaVerifik;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class VehicleInfoAuxDTO {
    private String placa;
    private String clase;
    private String tipo;
    private Integer cilindraje;
    private Integer modelo;
    private String marca;
    private String linea;
    private String nomotor;
    private String nochasis;
    private String novin;
    private Long identificacion;
    private String yyycomsoat;
    private String mmcomsoat;
    private String ddcomsoat;
    private String yyyvennusoat;
    private String mmvennusoat;
    private String ddvennusoat;
    private String codigotarifa;
    private String costototal;
    private String nombres;
    private String telefono;


    private String nonewsoat = "8004652365"; // Indica si el vehículo no tiene un nuevo SOAT
    private String prima = "$ 200.000"; // Prima del SOAT
    private String contribucion = "$ 120.000"; // Contribución del SOAT
    private String runt = "514278964265"; // Número RUNT del vehículo
    private Integer ocupantes = 5; // Número de ocupantes permitido
    private Double toneladas = 0.0; // Capacidad en toneladas

    private String compro = "NO";

    public VehicleInfoAuxDTO(VehicleInfoDTO infoDTO) {
        // Fecha inicial: 30 de noviembre de 2024
        LocalDate fechaInicial = LocalDate.of(2024, 11, 30);

        // Fecha final: sumar un año a la fecha inicial
        LocalDate fechaFinal = fechaInicial.plusYears(1);

        // Formatear las fechas
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");

        setPlaca(infoDTO.getPlate());
        setClase(infoDTO.getLine());
        setTipo(infoDTO.getServiceType());
        setCilindraje((infoDTO.getCylinderage()));
        setModelo((infoDTO.getModel()));
        setMarca(infoDTO.getBrand());
        setLinea(infoDTO.getLine());
        setNomotor(infoDTO.getEngineNumber());
        setNochasis(infoDTO.getChassisNumber());
        setNovin(infoDTO.getEngineNumber());
        setIdentificacion((infoDTO.getDocumento()));
        setYyycomsoat(fechaInicial.format(formatterYear));
        setMmcomsoat(fechaInicial.format(formatterMonth));
        setDdcomsoat(fechaInicial.format(formatterDay));

        setYyyvennusoat(fechaFinal.format(formatterYear));
        setMmvennusoat(fechaFinal.format(formatterMonth));
        setDdvennusoat(fechaFinal.format(formatterDay));

        setCodigotarifa(infoDTO.getTarifaType());
        setCostototal(infoDTO.getTarifaType());
        setNombres(infoDTO.getNombres());
        setTelefono(infoDTO.getTelefono());
    }
}
