package isi.deso.tpsolo.dao.interfaces;

import isi.deso.tpsolo.entidades.Usuario;

public interface IAutenticacionServicio {
    boolean autenticar(String user, String pass);
    void registrarNuevoUsuario(String user, String pass);
}