package isi.deso.tpsolo.repositorio;

import isi.deso.tpsolo.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.habitacion.id = :habId AND r.fechaInicio < :hasta AND r.fechaFin > :desde")
    List<Reserva> buscarReservasSuperpuestas(@Param("habId") Integer habId, @Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);

    @Modifying
    @Transactional
    void deleteByHuespedId(Long huespedId);
}