package isi.deso.tpsolo.dao;

import isi.deso.tpsolo.entidades.Usuario;
import java.io.*;
import java.util.*;
import isi.deso.tpsolo.dao.interfaces.IUsuarioDAO;

public class UsuarioDAOArchivo implements IUsuarioDAO {
    private String rutaArchivo = "usuarios.csv";

   @Override
public List<Usuario> listarTodos() {
    List<Usuario> lista = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(";");
            lista.add(new Usuario(p[0].trim(), p[1].trim()));
        }
    } catch (IOException e) {
        System.err.println(e.getMessage());
    }
    return lista;
}

@Override
public void guardar(Usuario u) {
    try (PrintWriter out = new PrintWriter(new FileWriter(rutaArchivo, true))) {
        out.println(u.getUsername() + ";" + u.getPassword());
    } catch (IOException e) {
        System.err.println("Error al guardar: " + e.getMessage());
    }
}
}