package isi.deso.tpsolo.dao; 
import isi.deso.tpsolo.entidades.Huesped; 
import java.io.*; 
import java.util.*; 
import isi.deso.tpsolo.dao.interfaces.IHuespedDAO; 
public class HuespedDAOArchivo implements IHuespedDAO { 
    private String ruta = "huespedes.csv"; 
    @Override public List<Huesped> listarTodos() { 
        List<Huesped> lista = new ArrayList<>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea; 
            while ((linea = br.readLine()) != null) { 
                if (linea.trim().isEmpty()) continue; 
                String[] p = linea.split(";"); 
                lista.add(new Huesped(p[0].trim(), p[1].trim(), p[2].trim())); 
            } 
        } 
        catch (IOException e) { 
            System.err.println("Error al leer: " + e.getMessage()); 
        } 
        return lista; 
    } 
   @Override
    public void guardar(Huesped h) {
        // El 'true' es para que lo agregue al final sin borrar los anteriores
        try (PrintWriter out = new PrintWriter(new FileWriter("huespedes.csv", true))) {
            out.println(h.getDni() + ";" + h.getNombre() + ";" + h.getApellido());
        } catch (IOException e) {
            System.err.println("Error al guardar huesped: " + e.getMessage());
        }
    }
    public void actualizarArchivo(List<Huesped> lista) {
       try (PrintWriter out = new PrintWriter(new FileWriter(ruta, false))) {
           for (Huesped h : lista) {
               out.println(h.getDni() + ";" + h.getNombre() + ";" + h.getApellido());
           }
       } catch (IOException e) {
           System.err.println("Error al actualizar archivo: " + e.getMessage());
       }
   }   
}