package isi.deso.tpsolo.repositorio;

import isi.deso.tpsolo.entidades.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface HuespedRepositorio extends JpaRepository<Huesped, Integer> {
    Optional<Huesped> findByDni(String dni);
    
    List<Huesped> findByActivoTrue();
    
    List<Huesped> findByActivoTrueAndDniContainingOrActivoTrueAndApellidoIgnoreCaseContaining(String dni, String apellido);
}