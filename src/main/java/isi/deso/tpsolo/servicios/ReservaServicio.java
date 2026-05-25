package isi.deso.tpsolo.servicios;

import isi.deso.tpsolo.entidades.Reserva;
import isi.deso.tpsolo.entidades.Huesped;
import isi.deso.tpsolo.entidades.Habitacion;
import isi.deso.tpsolo.repositorio.ReservaRepositorio;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    public Reserva reservar(Huesped huesped, Habitacion habitacion, LocalDate desde, LocalDate hasta) {
       
        List<Reserva> superpuestas = reservaRepositorio.buscarReservasSuperpuestas(habitacion.getId().intValue(), desde, hasta);
        
        if (!superpuestas.isEmpty()) {
            throw new IllegalArgumentException("La habitación ya está ocupada.");
        }

        Reserva nuevaReserva = new Reserva.Builder()
                .huesped(huesped)
                .habitacion(habitacion)
                .fechaInicio(desde)
                .fechaFin(hasta)
                .activa(true)
                .build();
        
        return reservaRepositorio.save(nuevaReserva);
    }
}