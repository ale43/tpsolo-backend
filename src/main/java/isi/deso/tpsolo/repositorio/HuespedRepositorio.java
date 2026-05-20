package isi.deso.tpsolo.repositorio;

import isi.deso.tpsolo.entidades.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface HuespedRepositorio extends JpaRepository<Huesped, Integer> {

    // Necesario para el alta/baja y buscar por DNI exacto
    Optional<Huesped> findByDni(String dni);

    // Necesario para la barra de búsqueda predictiva por DNI o Apellido
    List<Huesped> findByDniContainingOrApellidoIgnoreCaseContaining(String dni, String apellido);
}