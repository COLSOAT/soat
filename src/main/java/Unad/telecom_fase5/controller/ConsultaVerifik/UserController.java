package Unad.telecom_fase5.controller.ConsultaVerifik;

import Unad.telecom_fase5.entity.consultaVerifik.UserDTO;
import Unad.telecom_fase5.entity.consultaVerifik.UserEntity;
import Unad.telecom_fase5.entity.vehiculo.VehicleInfo;
import Unad.telecom_fase5.servicios.consultaVerifik.PasaDeTarifaSaldo;
import Unad.telecom_fase5.servicios.consultaVerifik.VehicleService;
import Unad.telecom_fase5.servicios.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/soat") // Ruta base
public class UserController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    UserService userService;

    @PostMapping("/consultar")
    public ResponseEntity<Map<String, Object>> showVehicle(@RequestBody UserDTO userDTO) {
        try {
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss a");
            String fechaFormateada = fechaActual.format(formato);
            UserEntity userEntity = new UserEntity();
            userEntity.setDocumento(userDTO.getDocumento());
            userEntity.setFecha(fechaFormateada);
            userEntity.setInformacion("PASO 1: (DATOS VEHICULO) INGRESO EL DOCUMENTO Y PLACA");
            VehicleInfo vehicleInfo = vehicleService.getVehicleInfo(userDTO.getDocumento(), userDTO.getPlaca());
            if (vehicleInfo != null) {
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
                userService.saveOrUpdateUser(userEntity);
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo de errores
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo de errores
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
        try {
            Iterable<UserEntity> users = userService.getAllUsers();
            if (users != null) {
                return ResponseEntity.ok(users);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // No hay contenido
            }
        } catch (Exception e) {
            e.printStackTrace(); // Registro adicional para depuración
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo de errores
        }
    }

    @DeleteMapping("/usuarios")
    public ResponseEntity<Iterable<UserEntity>> delete() {
        try {
           userService.deleteUserAll();
        } catch (Exception e) {
            e.printStackTrace(); // Registro adicional para depuración
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo de errores
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo de errores
    }


}
