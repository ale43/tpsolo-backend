package isi.deso.tpsolo.entidades;

import isi.deso.tpsolo.decoradores.ComponenteHabitacion;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "habitaciones")
public class Habitacion implements ComponenteHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String piso;
    private String categoria; 
    
    @Column(name = "precio_base")
    private BigDecimal precioBase;

    public Habitacion() {
    }

    public Habitacion(String numero, String piso, String category, BigDecimal precioBase) {
        this.numero = numero;
        this.piso = piso;
        this.categoria = category;
        this.precioBase = precioBase;
    }

    // 🌟 MÉTODOS DEL PATRÓN DECORATOR
    @Override
    public BigDecimal getPrecioNoche() {
        return this.precioBase;
    }

    @Override
    public String getDescripcion() {
        return "Habitación " + numero + " (" + categoria + ")";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getPiso() { return piso; }
    public void setPiso(String piso) { this.piso = piso; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }
}