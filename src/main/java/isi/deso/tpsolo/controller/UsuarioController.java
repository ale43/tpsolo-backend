package isi.deso.tpsolo.controller;

import isi.deso.tpsolo.entidades.Usuario;
import isi.deso.tpsolo.repositorio.UsuarioRepositorio;
import isi.deso.tpsolo.servicios.AutenticacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private AutenticacionServicio autenticacionServicio;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario nuevoUsuario) {
        try {
            Usuario guardado = usuarioRepositorio.save(nuevoUsuario);
            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el usuario: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody Usuario loginRequest) {
        try {
           
            boolean esValido = autenticacionServicio.autenticar(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            );
            
            return ResponseEntity.ok(esValido);
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) { 
        try {
            if (!usuarioRepositorio.existsById(id)) {
                return ResponseEntity.status(404).body("El usuario no existe.");
            }
            usuarioRepositorio.deleteById(id);
            return ResponseEntity.ok().body("Usuario eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar: " + e.getMessage());
        }
    }
}