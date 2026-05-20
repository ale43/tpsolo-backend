package isi.deso.tpsolo.repositorio;

import isi.deso.tpsolo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    
    Usuario findByUsername(String username);
}