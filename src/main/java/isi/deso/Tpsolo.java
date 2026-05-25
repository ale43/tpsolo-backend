package isi.deso;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class Tpsolo {

    public static void main(String[] args) {
       
        SpringApplication.run(Tpsolo.class, args);
        
        System.out.println("\n--- SERVIDOR DEL HOTEL ENCENDIDO ---");
        System.out.println("Backend (API) corriendo en: http://localhost:8081");
        System.out.println("Frontend (Web) corriendo en: http://localhost:3000");
    }
}