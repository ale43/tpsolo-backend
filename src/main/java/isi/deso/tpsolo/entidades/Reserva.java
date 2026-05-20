package isi.deso.tpsolo.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reservas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "huesped_id", nullable = false)
    private Huesped huesped;

    @ManyToOne
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Habitacion habitacion;

    @Column(name = "fecha_inicio")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;

    private boolean activa;

    // 1. Constructor vacío obligatorio para JPA
    public Reserva() {
    }

    // 2. Constructor parametrizado
    public Reserva(Huesped huesped, Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activa = true; 
    }

    // 3. Constructor privado que utiliza el Builder
    private Reserva(Builder builder) {
        this.id = builder.id;
        this.huesped = builder.huesped;
        this.habitacion = builder.habitacion;
        this.fechaInicio = builder.fechaInicio;
        this.fechaFin = builder.fechaFin;
        this.activa = builder.activa;
    }

    // 4. PATRÓN BUILDER (Clase interna estática)
    public static class Builder {
        private Long id;
        private Huesped huesped;
        private Habitacion habitacion;
        private LocalDate fechaInicio;
        private LocalDate fechaFin;
        private boolean activa = true;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder huesped(Huesped huesped) {
            this.huesped = huesped;
            return this;
        }

        public Builder habitacion(Habitacion habitacion) {
            this.habitacion = habitacion;
            return this;
        }

        public Builder fechaInicio(LocalDate fechaInicio) {
            this.fechaInicio = fechaInicio; // 🌟 ¡CORREGIDO ACÁ!
            return this;
        }

        public Builder fechaFin(LocalDate fechaFin) {
            this.fechaFin = fechaFin;
            return this;
        }

        public Builder activa(boolean activa) {
            this.activa = activa;
            return this;
        }

        public Reserva build() {
            return new Reserva(this);
        }
    }

    // 5. Getters y Setters tradicionales y de soporte para Jackson
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Huesped getHuesped() { return huesped; }
    public void setHuesped(Huesped huesped) { this.huesped = huesped; }

    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    // Interceptor de strings para evitar Error 400 si Jackson no posee el módulo JSR310 activo
    public void setFechaInicio(String fecha) {
        if (fecha != null && !fecha.isEmpty()) {
            this.fechaInicio = LocalDate.parse(fecha);
        }
    }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    // Interceptor de strings para la fecha de finalización
    public void setFechaFin(String fecha) {
        if (fecha != null && !fecha.isEmpty()) {
            this.fechaFin = LocalDate.parse(fecha);
        }
    }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}