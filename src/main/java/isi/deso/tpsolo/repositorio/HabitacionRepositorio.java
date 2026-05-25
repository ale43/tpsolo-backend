package isi.deso.tpsolo.repositorio;

import isi.deso.tpsolo.entidades.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepositorio extends JpaRepository<Habitacion, Integer> {
}