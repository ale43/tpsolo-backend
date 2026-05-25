package isi.deso.tpsolo.controller;
 
import isi.deso.tpsolo.entidades.Factura;
import isi.deso.tpsolo.servicios.FacturaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.Map;
 
@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = "http://localhost:3000")
public class FacturaController {
 
    @Autowired
    private FacturaServicio facturaServicio;
 
    @PostMapping("/generar")
    public ResponseEntity<?> generarFactura(@RequestBody Map<String, Object> payload) {
        try {
            Factura factura = facturaServicio.generarFactura(payload);
            return ResponseEntity.ok(factura);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al generar factura: " + e.getMessage());
        }
    }
 
    @GetMapping
    public List<Factura> listarTodas() {
        return facturaServicio.listarTodas();
    }
 
    @GetMapping("/huesped/{dni}")
    public List<Factura> listarPorHuesped(@PathVariable String dni) {
        return facturaServicio.listarPorHuesped(dni);
    }
 
    @GetMapping("/reserva/{id}")
    public List<Factura> listarPorReserva(@PathVariable Long id) {
        return facturaServicio.listarPorReserva(id);
    }
}