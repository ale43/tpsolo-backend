package isi.deso.tpsolo.servicios;

import isi.deso.tpsolo.entidades.Habitacion;
import isi.deso.tpsolo.repositorio.HabitacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service 
public class HabitacionServicio implements IHabitacionServicio {
    
    @Autowired
    private HabitacionRepositorio habitacionRepositorio; 

   @Override
public List<Habitacion> obtenerHabitacionesDisponibles() {
    return habitacionRepositorio.findAll();
}
    @Override
    public void agregarHabitacion(Habitacion habitacion) {
        habitacionRepositorio.save(habitacion);
    }
}