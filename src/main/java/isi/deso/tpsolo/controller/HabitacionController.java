package isi.deso.tpsolo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import isi.deso.tpsolo.entidades.Habitacion;
import isi.deso.tpsolo.servicios.IHabitacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private IHabitacionServicio habitacionServicio;

    @GetMapping
    public List<Habitacion> getDisponibles() {
        return habitacionServicio.obtenerHabitacionesDisponibles();
    }
}