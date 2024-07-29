package Unad.telecom_fase5.entity.vehiculo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) // Ignorar campos desconocidos globalmente
public class VehicleInfo {

    @JsonProperty("data")
    private VehicleData data;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true) // Ignorar campos desconocidos en esta clase también
    public static class VehicleData {
        @JsonProperty("datosTecnicos")
        private TechnicalData technicalData;

        @JsonProperty("informacionGeneral")
        private GeneralInformation generalInformation;

        @JsonProperty("soat")
        private List<Soat> soat;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true) // Ignorar campos desconocidos en esta clase también
    public static class TechnicalData {
        @JsonProperty("noEjes")
        private String numberOfAxles;

        @JsonProperty("pasajerosSentados")
        private String seatedPassengers;

        @JsonProperty("pesoBrutoVehicular")
        private String grossVehicleWeight;

        // Asegúrate de no tener campos no definidos en el JSON recibido
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true) // Ignorar campos desconocidos en esta clase también
    public static class GeneralInformation {
        @JsonProperty("noPlaca")
        private String plate;

        @JsonProperty("tipoServicio")
        private String serviceType;

        @JsonProperty("modelo")
        private String model;

        @JsonProperty("marca")
        private String brand;

        @JsonProperty("linea")
        private String line;

        @JsonProperty("cilidraje")
        private String cylinderage;

        @JsonProperty("noMotor")
        private String engineNumber;

        @JsonProperty("noChasis")
        private String chassisNumber;

        // Asegúrate de no tener campos no definidos en el JSON recibido
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true) // Ignorar campos desconocidos en esta clase también
    public static class Soat {
        @JsonProperty("tipoTarifa")
        private String tarifaType;

        // Otros campos si es necesario
    }
}
