package isi.deso.tpsolo.repositorio;

import isi.deso.tpsolo.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.habitacion.id = :habitacionId " +
           "AND r.activa = true " +
           "AND :fechaInicio <= r.fechaFin " +
           "AND :fechaFin >= r.fechaInicio")
    List<Reserva> buscarReservasSuperpuestas(
        @Param("habitacionId") Integer habitacionId, 
        @Param("fechaInicio") LocalDate fechaInicio, 
        @Param("fechaFin") LocalDate fechaFin
    );
}