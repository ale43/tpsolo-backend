package isi.deso.tpsolo.dao.interfaces;

import isi.deso.tpsolo.entidades.Huesped;
import java.util.List;

public interface IHuespedDAO {
    List<Huesped> listarTodos();
    void guardar(Huesped huesped); 
    void actualizarArchivo(List<Huesped> lista);
}