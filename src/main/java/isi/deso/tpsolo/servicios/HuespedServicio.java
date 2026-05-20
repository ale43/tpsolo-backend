package isi.deso.tpsolo.servicios;

import isi.deso.tpsolo.dao.interfaces.IHuespedServicio;
import isi.deso.tpsolo.entidades.Huesped;
import isi.deso.tpsolo.repositorio.HuespedRepositorio; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import java.util.List;

@Service
public class HuespedServicio implements IHuespedServicio {
    
    @Autowired
    private HuespedRepositorio huespedRepositorio; 

    public List<Huesped> getTodosLosHuespedes() {
        return huespedRepositorio.findAll();
    }

    public Huesped buscarPorDni(String dni) {
        return huespedRepositorio.findByDni(dni).orElse(null);
    }

    @Override
    public List<Huesped> buscarHuespedes(String termino) {
        return huespedRepositorio.findByDniContainingOrApellidoIgnoreCaseContaining(termino, termino);
    }

    @Override
   
public void darDeAlta(Huesped nuevo) {
    String dniLimpio = nuevo.getDni().trim();
    
    if (buscarPorDni(dniLimpio) != null) {
        throw new IllegalArgumentException("El número de DNI " + dniLimpio + " ya se encuentra registrado.");
    }

    // Si no existe, guarda normalmente
    nuevo.setDni(dniLimpio);
    huespedRepositorio.save(nuevo); 
}
    public Huesped modificarHuesped(String dni, Huesped datosNuevos) {
        Huesped huespedExistente = buscarPorDni(dni);
        if (huespedExistente != null) {
            huespedExistente.setNombre(datosNuevos.getNombre());
            huespedExistente.setApellido(datosNuevos.getApellido());
            huespedExistente.setEmail(datosNuevos.getEmail());
            huespedExistente.setTelefono(datosNuevos.getTelefono());
            huespedExistente.setDireccion(datosNuevos.getDireccion());
            huespedExistente.setPosicionIva(datosNuevos.getPosicionIva());
            
            return huespedRepositorio.save(huespedExistente);
        }
        return null;
    }

    @Override
    public void darDeBaja(String dni) {
        Huesped aBorrar = buscarPorDni(dni);
        if (aBorrar != null) {
            huespedRepositorio.delete(aBorrar); 
            System.out.println(">> Sistema: Huesped con DNI " + dni + " eliminado de Postgres.");
        } else {
            System.out.println(">> Error Crítico: No se encontró ningún huésped con el DNI: " + dni);
        }
    }
}