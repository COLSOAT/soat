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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/soat") // Ruta base
public class GenerarSoatPdfController {

    @Autowired
    UserService userService;

    @PostMapping("/documento")
    public void documento(HttpServletResponse response, @RequestBody VehicleInfoDTO vehicleInfoDTO) {


        try {
            LocalDateTime fechaActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss a");
            String fechaFormateada = fechaActual.format(formato);
            UserEntity userEntity = new UserEntity();
            userEntity.setDocumento(String.valueOf(vehicleInfoDTO.getDocumento()));
            userEntity.setFecha(fechaFormateada);
            userEntity.setInformacion("PASO 3:(SOAT) SE ENTREGO EL SOAT");
            Soat soat = new Soat(new VehicleInfoAuxDTO(vehicleInfoDTO));
            byte[] pdfReport = soat.generarSOAT();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"reporte.pdf\"");
            response.setContentLength(pdfReport.length);
            ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);
            FileCopyUtils.copy(inStream, response.getOutputStream());

            userService.saveOrUpdateUser(userEntity);
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Error al procesar la solicitud: " + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Error inesperado: " + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
