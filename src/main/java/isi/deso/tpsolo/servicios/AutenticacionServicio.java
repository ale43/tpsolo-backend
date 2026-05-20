package isi.deso.tpsolo.servicios;

import isi.deso.tpsolo.dao.interfaces.IAutenticacionServicio;
import isi.deso.tpsolo.entidades.Usuario;
import isi.deso.tpsolo.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionServicio implements IAutenticacionServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public boolean autenticar(String user, String pass) {
        // Buscamos el usuario de la base de datos
        Usuario u = usuarioRepositorio.findByUsername(user);
        
        // Si el usuario existe y la contraseña coincide, devuelve true. Si no, false.
        if (u != null && u.getPassword() != null) {
            return u.getPassword().equals(pass);
        }
        return false;
    }

    @Override
    public void registrarNuevoUsuario(String user, String pass) {
        Usuario nuevo = new Usuario(user, pass);
        usuarioRepositorio.save(nuevo);
        System.out.println(">> Sistema: Usuario " + user + " registrado en Postgres con exito.");
    }
}