package isi.deso.tpsolo.controller; 

import isi.deso.tpsolo.entidades.Huesped;
import isi.deso.tpsolo.servicios.HuespedServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.List;

@RestController
@RequestMapping("/huespedes")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class HuespedController {

    @Autowired
    private HuespedServicio huespedServicio;

    @GetMapping
    public List<Huesped> listarTodos() {
        return huespedServicio.getTodosLosHuespedes();
    }

    @GetMapping("/buscar")
    public List<Huesped> buscar(@RequestParam String termino) {
        return huespedServicio.buscarHuespedes(termino);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Huesped nuevo) {
        try {
            // Intentamos dar de alta desde el servicio
            huespedServicio.darDeAlta(nuevo);
            return ResponseEntity.ok("Huésped registrado con éxito.");
        } catch (IllegalArgumentException e) {
            // Si el servicio tiró el error de DNI duplicado, lo mandamos al Front con código 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Cualquier otro fallo inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado en el servidor: " + e.getMessage());
        }
    }

    @PutMapping("/{dni}")
    public ResponseEntity<?> modificar(@PathVariable String dni, @RequestBody Huesped datosNuevos) {
        Huesped actualizado = huespedServicio.modificarHuesped(dni, datosNuevos);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró ningún huésped con el DNI proporcionado.");
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<String> eliminar(@PathVariable String dni) {
        try {
            huespedServicio.darDeBaja(dni); 
            return ResponseEntity.ok("Huésped eliminado correctamente de la base de datos.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se puede eliminar porque ese huésped tiene una reserva activa.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servidor al procesar la baja: " + e.getMessage());
        }
    }
}