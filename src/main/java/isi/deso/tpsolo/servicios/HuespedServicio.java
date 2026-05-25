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
        return huespedRepositorio.findByActivoTrue();
    }

    public Huesped buscarPorDni(String dni) {
        return huespedRepositorio.findByDni(dni).filter(Huesped::isActivo).orElse(null);
    }

    @Override
    public List<Huesped> buscarHuespedes(String termino) {
        return huespedRepositorio.findByActivoTrueAndDniContainingOrActivoTrueAndApellidoIgnoreCaseContaining(termino, termino);
    }

    @Override
    public void darDeAlta(Huesped nuevo) {
        Huesped existente = buscarPorDni(nuevo.getDni().trim());
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