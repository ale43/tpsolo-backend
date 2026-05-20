package isi.deso.tpsolo.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "medios_pago")
@Inheritance(strategy = InheritanceType.JOINED) // Crea tablas separadas unidas por ID
public abstract class MedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal monto;

    public MedioPago() {
    }

    public MedioPago(BigDecimal monto) {
        this.monto = monto;
    }

    // Método abstracto que cada medio de pago implementará a su manera
    public abstract void procesarPago();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
}