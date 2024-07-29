package Unad.telecom_fase5.controller.soat;

import Unad.telecom_fase5.entity.consultaVerifik.VehicleInfoAuxDTO;
import Unad.telecom_fase5.entity.consultaVerifik.VehicleInfoDTO;
import Unad.telecom_fase5.entity.soat.Soat;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RequestMapping("/soat")
@RestController
public class GenerarSoatPdfController {

    @PostMapping(value = "/documento")
    public void documento(HttpServletResponse response, @RequestBody VehicleInfoDTO vehicleInfoDTO) {
        System.out.println(vehicleInfoDTO.toString()+"-oscar");

        Soat soat = new Soat(new VehicleInfoAuxDTO(vehicleInfoDTO));
        byte[] pdfReport = soat.generarSOAT();
        response.setContentType("application/pdf");
        // Cambia la disposición del contenido para que se abra en una nueva pestaña
        response.setHeader("Content-Disposition", "inline; filename=\"reporte.pdf\"");
        response.setContentLength(pdfReport.length);
        ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);
        try {
            FileCopyUtils.copy(inStream, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
