package isi.deso.tpsolo.repositorio;
 
import isi.deso.tpsolo.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface FacturaRepositorio extends JpaRepository<Factura, Long> {
 
    List<Factura> findByHuespedDniOrderByFechaEmisionDesc(String huespedDni);
 
    List<Factura> findByReservaIdOrderByFechaEmisionDesc(Long reservaId);
 
    @org.springframework.transaction.annotation.Transactional
    void deleteByReservaId(Long reservaId);
}
 