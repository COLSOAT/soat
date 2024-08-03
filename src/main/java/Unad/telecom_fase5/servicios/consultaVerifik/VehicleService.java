package Unad.telecom_fase5.servicios.consultaVerifik;

import Unad.telecom_fase5.UTILS;
import Unad.telecom_fase5.entity.vehiculo.VehicleInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public VehicleInfo getVehicleInfo(String documentNumber, String plate) throws Exception {
        String url = String.format("https://api.verifik.co/v2/co/runt/vehicle-by-plate?documentType=%s&documentNumber=%s&plate=%s",
                "CC", documentNumber, plate);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.setHeader("Accept", "application/json");
            request.setHeader("Authorization", "Bearer "+ UTILS.getKeyVerifica());

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    String jsonResponse = EntityUtils.toString(response.getEntity());
                    // Mapea la respuesta JSON a un objeto VehicleInfo
                    VehicleInfo vehicleInfo = objectMapper.readValue(jsonResponse, VehicleInfo.class);
                    return vehicleInfo;
                } else {
                    throw new RuntimeException("Failed to get vehicle information. HTTP code: " + response.getStatusLine().getStatusCode());
                }
            }
        }
    }
}
