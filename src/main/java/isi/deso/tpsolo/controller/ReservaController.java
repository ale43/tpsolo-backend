package isi.deso.tpsolo.controller;
 
import isi.deso.tpsolo.entidades.Habitacion;
import isi.deso.tpsolo.entidades.Huesped;
import isi.deso.tpsolo.entidades.Reserva;
import isi.deso.tpsolo.repositorio.FacturaRepositorio;
import isi.deso.tpsolo.repositorio.HabitacionRepositorio;
import isi.deso.tpsolo.repositorio.HuespedRepositorio;
import isi.deso.tpsolo.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
 
@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaController {
 
    @Autowired
    private ReservaRepositorio reservaRepositorio;
 
    @Autowired
    private FacturaRepositorio facturaRepositorio;
 
    @Autowired
    private HuespedRepositorio huespedRepositorio;
 
    @Autowired
    private HabitacionRepositorio habitacionRepositorio;
 
    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody Map<String, Object> payload) {
        System.out.println("DEBUG: El JSON que llega al servidor es: " + payload);
        try {
            Map<String, String> huespedData = (Map<String, String>) payload.get("huesped");
            String dni = huespedData.get("dni");
 
            Map<String, Object> habData = (Map<String, Object>) payload.get("habitacion");
            Integer habitacionId = Integer.valueOf(habData.get("id").toString());
 
            Huesped h = huespedRepositorio.findByDni(dni).orElse(null);
            Habitacion hab = habitacionRepositorio.findById(habitacionId).orElse(null);
 
            if (h == null) return ResponseEntity.badRequest().body("Huésped no encontrado con DNI: " + dni);
            if (hab == null) return ResponseEntity.badRequest().body("Habitación no encontrada con ID: " + habitacionId);
 
            Reserva nuevaReserva = new Reserva.Builder()
                    .huesped(h)
                    .habitacion(hab)
                    .fechaInicio(LocalDate.parse(payload.get("fechaInicio").toString()))
                    .fechaFin(LocalDate.parse(payload.get("fechaFin").toString()))
                    .activa(true)
                    .build();
 
            reservaRepositorio.save(nuevaReserva);
            return ResponseEntity.ok(nuevaReserva);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear reserva: " + e.getMessage());
        }
    }
 
    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaRepositorio.findAll();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerReservaPorId(@PathVariable Long id) {
        return reservaRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
 
    @DeleteMapping("/{id}")
    @org.springframework.transaction.annotation.Transactional
    public ResponseEntity<?> eliminarReserva(@PathVariable Long id) {
        if (!reservaRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        facturaRepositorio.deleteByReservaId(id);
        reservaRepositorio.deleteById(id);
        return ResponseEntity.ok("Reserva eliminada con éxito");
    }
}