package isi.deso.tpsolo.dao.interfaces;

import isi.deso.tpsolo.entidades.Huesped;
import java.util.List;

public interface IHuespedServicio {
    
    List<Huesped> getTodosLosHuespedes();
    Huesped buscarPorDni(String dni);
    void darDeAlta(Huesped nuevo);
    void darDeBaja(String dni);
    
    List<Huesped> buscarHuespedes(String termino);
}