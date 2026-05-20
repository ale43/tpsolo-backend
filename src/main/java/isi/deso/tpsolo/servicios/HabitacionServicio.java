package isi.deso.tpsolo.servicios;

import isi.deso.tpsolo.entidades.Habitacion;
import isi.deso.tpsolo.repositorio.HabitacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // <- OBLIGATORIO para que Spring Boot reconozca el servicio
public class HabitacionServicio implements IHabitacionServicio {
    
    @Autowired
    private HabitacionRepositorio habitacionRepositorio; // <- Inyectamos el acceso a Postgres

   @Override
public List<Habitacion> obtenerHabitacionesDisponibles() {
    // Traemos todo de una para ver si el mapeo base funciona
    return habitacionRepositorio.findAll();
}
    @Override
    public void agregarHabitacion(Habitacion habitacion) {
        // Guarda una nueva habitación físicamente en Postgres
        habitacionRepositorio.save(habitacion);
    }
}