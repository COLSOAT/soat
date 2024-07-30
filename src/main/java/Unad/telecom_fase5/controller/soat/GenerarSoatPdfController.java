package Unad.telecom_fase5.controller.soat;

import Unad.telecom_fase5.entity.consultaVerifik.UserEntity;
import Unad.telecom_fase5.entity.consultaVerifik.VehicleInfoAuxDTO;
import Unad.telecom_fase5.entity.consultaVerifik.VehicleInfoDTO;
import Unad.telecom_fase5.entity.soat.Soat;
import Unad.telecom_fase5.servicios.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequestMapping("/soat")
@RestController
public class GenerarSoatPdfController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/documento")
    public void documento(HttpServletResponse response, @RequestBody VehicleInfoDTO vehicleInfoDTO) {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formato);
        UserEntity userEntity = new UserEntity();
        userEntity.setDocumento(String.valueOf(vehicleInfoDTO.getDocumento()));
        userEntity.setFecha(fechaFormateada);
        userEntity.setInformacion("PASO 3:(SOAT) SE ENTREGO EL SOAT");

        Soat soat = new Soat(new VehicleInfoAuxDTO(vehicleInfoDTO));
        byte[] pdfReport = soat.generarSOAT();
        response.setContentType("application/pdf");
        // Cambia la disposición del contenido para que se abra en una nueva pestaña
        response.setHeader("Content-Disposition", "inline; filename=\"reporte.pdf\"");
        response.setContentLength(pdfReport.length);
        ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

        try {
            FileCopyUtils.copy(inStream, response.getOutputStream());
            userService.saveOrUpdateUser(userEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
