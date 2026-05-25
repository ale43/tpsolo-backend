package isi.deso.tpsolo.servicios;
 
import isi.deso.tpsolo.dao.interfaces.IHuespedServicio;
import isi.deso.tpsolo.entidades.Huesped;
import isi.deso.tpsolo.repositorio.HuespedRepositorio;
import isi.deso.tpsolo.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
 
@Service
public class HuespedServicio implements IHuespedServicio {
 
    @Autowired
    private HuespedRepositorio huespedRepositorio;
 
    @Autowired
    private ReservaRepositorio reservaRepositorio;
 
    public List<Huesped> getTodosLosHuespedes() {
        // ✅ Solo devuelve huéspedes activos Y con DNI válido
        return huespedRepositorio.findByActivoTrue()
                .stream()
                .filter(h -> h.getDni() != null && !h.getDni().trim().isEmpty())
                .toList();
    }
 
    public Huesped buscarPorDni(String dni) {
        return huespedRepositorio.findByDni(dni).filter(Huesped::isActivo).orElse(null);
    }
 
    @Override
    public List<Huesped> buscarHuespedes(String termino) {
        return huespedRepositorio
                .findByActivoTrueAndDniContainingOrActivoTrueAndApellidoIgnoreCaseContaining(termino, termino)
                .stream()
                .filter(h -> h.getDni() != null && !h.getDni().trim().isEmpty())
                .toList();
    }
 
    @Override
    public void darDeAlta(Huesped nuevo) {
        // ✅ Validaciones antes de guardar
        if (nuevo.getDni() == null || nuevo.getDni().trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio y no puede estar vacío.");
        }
        if (nuevo.getNombre() == null || nuevo.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (nuevo.getApellido() == null || nuevo.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
 
        // Normalizamos el DNI (sin espacios)
        nuevo.setDni(nuevo.getDni().trim());
 
        Huesped existente = buscarPorDni(nuevo.getDni());
        if (existente != null) {
            throw new IllegalArgumentException("El DNI " + nuevo.getDni() + " ya está registrado.");
        }
 
        huespedRepositorio.save(nuevo);
    }
 
    @Override
    @Transactional
    public void darDeBaja(String dni) {
        Huesped h = buscarPorDni(dni);
        if (h != null) {
            reservaRepositorio.deleteByHuespedId(h.getId());
            huespedRepositorio.delete(h);
        }
    }
 
    public Huesped modificarHuesped(String dni, Huesped datosNuevos) {
        Huesped h = buscarPorDni(dni);
        if (h != null) {
            h.setNombre(datosNuevos.getNombre());
            h.setApellido(datosNuevos.getApellido());
            h.setEmail(datosNuevos.getEmail());
            h.setTelefono(datosNuevos.getTelefono());
            h.setDireccion(datosNuevos.getDireccion());
            h.setPosicionIva(datosNuevos.getPosicionIva());
            return huespedRepositorio.save(h);
        }
        return null;
    }
}