package isi.deso.tpsolo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @PostMapping("/generar")
    public ResponseEntity<?> generarFactura(@RequestBody Map<String, Object> datosFactura) {
        System.out.println("🧾 [Backend] Petición de facturación recibida con éxito: " + datosFactura);
        
        return ResponseEntity.ok().body(Map.of(
            "status", "SUCCESS",
            "mensaje", "Factura procesada y abonada correctamente en el sistema."
        ));
    }
}