package isi.deso.tpsolo.dao.interfaces;
import isi.deso.tpsolo.entidades.Usuario;
import java.util.List;

public interface IUsuarioDAO {
    List<Usuario> listarTodos(); 
     void guardar(Usuario usuario);
}
