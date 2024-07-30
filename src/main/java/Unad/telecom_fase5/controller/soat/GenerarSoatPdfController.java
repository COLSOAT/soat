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
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss a");
            String fechaFormateada = fechaActual.format(formato);
            UserEntity userEntity = new UserEntity();
            userEntity.setDocumento(String.valueOf(vehicleInfoDTO.getDocumento()));
            userEntity.setFecha(fechaFormateada);
            userEntity.setInformacion("PASO 3:(SOAT) SE ENTREGO EL SOAT");
            System.out.println(userEntity.toString());

            Soat soat = new Soat(new VehicleInfoAuxDTO(vehicleInfoDTO));
            byte[] pdfReport = soat.generarSOAT();

            if (pdfReport == null || pdfReport.length == 0) {
                throw new RuntimeException("Error al generar el reporte PDF: el archivo PDF está vacío.");
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"reporte.pdf\"");
            response.setContentLength(pdfReport.length);
            ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);
            FileCopyUtils.copy(inStream, response.getOutputStream());

            userService.saveOrUpdateUser(userEntity);
            System.out.println("Aqui esta todo bien 1 - "+pdfReport.length);
            System.out.println("Aqui esta todo bien 2 - "+soat.getVehiculo().getCostototal());
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Error al procesar la solicitud: " + e.getMessage());
                System.out.println(e.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Error inesperado: " + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
