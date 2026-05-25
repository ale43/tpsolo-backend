package isi.deso.tpsolo.servicios;

import isi.deso.tpsolo.entidades.Habitacion;
import java.util.List;

public interface IHabitacionServicio {
    List<Habitacion> obtenerHabitacionesDisponibles();
    void agregarHabitacion(Habitacion habitacion); 
}