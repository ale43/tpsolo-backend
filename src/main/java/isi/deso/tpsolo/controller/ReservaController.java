package isi.deso.tpsolo.controller;

import isi.deso.tpsolo.entidades.Habitacion;
import isi.deso.tpsolo.entidades.Huesped;
import isi.deso.tpsolo.entidades.Reserva;
import isi.deso.tpsolo.servicios.HuespedServicio;
import isi.deso.tpsolo.servicios.ReservaServicio;
import isi.deso.tpsolo.repositorio.HabitacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}) 
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private HuespedServicio huespedServicio;

    @Autowired
    private HabitacionRepositorio habitacionRepositorio;

    @GetMapping
    public List<Reserva> listar() {
        return reservaServicio.obtenerTodas();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/huesped/{dni}")
    public ResponseEntity<?> obtenerPorDniHuesped(@PathVariable String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return ResponseEntity.status(400).body("Error: El DNI provisto no es válido.");
        }

        List<Reserva> todas = reservaServicio.obtenerTodas();
        
        Reserva encontrada = todas.stream()
                .filter(r -> r.isActiva() && 
                             r.getHuesped() != null && 
                             r.getHuesped().getDni() != null && 
                             r.getHuesped().getDni().trim().equalsIgnoreCase(dni.trim()))
                .findFirst()
                .orElse(null);
                
        if (encontrada == null) {
            return ResponseEntity.status(404).body("No se encontró ninguna reserva activa para el DNI: " + dni);
        }
        
        return ResponseEntity.ok(encontrada);
    }

    @PostMapping
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> datos) {
        try {
            String dni = null;
            if (datos.containsKey("dni") && datos.get("dni") != null) {
                dni = datos.get("dni").toString();
            } else if (datos.containsKey("huesped") && datos.get("huesped") instanceof Map) {
                Map<String, Object> subMapHuesped = (Map<String, Object>) datos.get("huesped");
                if (subMapHuesped.containsKey("dni") && subMapHuesped.get("dni") != null) {
                    dni = subMapHuesped.get("dni").toString();
                }
            }

            if (dni == null || dni.trim().isEmpty()) {
                return ResponseEntity.status(400).body("Error: El DNI del huésped es obligatorio y no fue enviado de forma válida.");
            }

            Huesped huesped = huespedServicio.buscarPorDni(dni.trim());
            if (huesped == null) {
                return ResponseEntity.status(404).body("No se encontró el huésped con el DNI especificado.");
            }
            
            String habitacionIdStr = null;
            if (datos.containsKey("habitacionId") && datos.get("habitacionId") != null) {
                habitacionIdStr = datos.get("habitacionId").toString();
            } else if (datos.containsKey("habitacion") && datos.get("habitacion") instanceof Map) {
                Map<String, Object> subMapHab = (Map<String, Object>) datos.get("habitacion");
                if (subMapHab.containsKey("id") && subMapHab.get("id") != null) {
                    habitacionIdStr = subMapHab.get("id").toString();
                }
            }

            if (habitacionIdStr == null || habitacionIdStr.trim().isEmpty()) {
                return ResponseEntity.status(400).body("Error: El identificador de la habitación es obligatorio.");
            }

            Integer idHab = Integer.parseInt(habitacionIdStr.trim());
            Habitacion hab = habitacionRepositorio.findById(idHab)
                    .orElseThrow(() -> new RuntimeException("La habitación no existe en la base de datos."));

            String fechaDesdeStr = datos.containsKey("desde") ? datos.get("desde").toString() : 
                                  (datos.containsKey("fechaInicio") ? datos.get("fechaInicio").toString() : null);
            
            String fechaHastaStr = datos.containsKey("hasta") ? datos.get("hasta").toString() : 
                                  (datos.containsKey("fechaFin") ? datos.get("fechaFin").toString() : null);

            if (fechaDesdeStr == null || fechaHastaStr == null) {
                return ResponseEntity.status(400).body("Error: Las fechas 'desde' y 'hasta' son obligatorias.");
            }

            // 4. Guardamos la reserva invocando la validación de superposición
            Reserva nuevaReserva = reservaServicio.reservar(
                huesped, 
                hab, 
                LocalDate.parse(fechaDesdeStr), 
                LocalDate.parse(fechaHastaStr)
            );
            return ResponseEntity.ok(nuevaReserva);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear la reserva: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long id) {
        try {
            reservaServicio.eliminarReservaPorId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar la reserva: " + e.getMessage());
        }
    }
}