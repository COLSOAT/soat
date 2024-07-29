package Unad.telecom_fase5.controller.ConsultaVerifik;

import Unad.telecom_fase5.entity.consultaVerifik.UserDTO;
import Unad.telecom_fase5.entity.vehiculo.VehicleInfo;
import Unad.telecom_fase5.servicios.consultaVerifik.PasaDeTarifaSaldo;
import Unad.telecom_fase5.servicios.consultaVerifik.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/soat")
public class UserController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/consultar")
    public ResponseEntity<Map<String, Object>> showVehicle(@RequestBody UserDTO userDTO) {
        try {
            // Obtener la información del vehículo
            VehicleInfo vehicleInfo = vehicleService.getVehicleInfo(userDTO.getDocumento(), userDTO.getPlaca());
            if (vehicleInfo != null) {
                // Extraer la información necesaria del objeto VehicleInfo
                VehicleInfo.VehicleData data = vehicleInfo.getData();
                VehicleInfo.GeneralInformation generalInformation = data.getGeneralInformation();

                Map<String, Object> response = new HashMap<>();
                response.put("plate", generalInformation.getPlate());
                response.put("serviceType", generalInformation.getServiceType());
                response.put("model", generalInformation.getModel());
                response.put("brand", generalInformation.getBrand());
                response.put("line", generalInformation.getLine());
                response.put("cylinderage", generalInformation.getCylinderage());
                response.put("engineNumber", generalInformation.getEngineNumber());
                response.put("chassisNumber", generalInformation.getChassisNumber());
                PasaDeTarifaSaldo pasaDeTarifaSaldo = new PasaDeTarifaSaldo();
                response.put("tarifaType", pasaDeTarifaSaldo.getValueInCOP(Integer.valueOf(vehicleInfo.getData().getSoat().get(0).getTarifaType())));
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo de errores
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo de errores
    }


}
