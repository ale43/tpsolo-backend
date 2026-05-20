package isi.deso.tpsolo.entidades;

import isi.deso.tpsolo.strategy.CalculoEstadiaStrategy;
import isi.deso.tpsolo.strategy.PrecioNormalStrategy;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    private BigDecimal total;

    @OneToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;

    // 🌟 EL CORAZÓN DEL PATRÓN STRATEGY
    // @Transient le dice a Hibernate que NO cree una columna para esto en Postgres
    @Transient
    private CalculoEstadiaStrategy estrategiaCalculo;

    // Constructor vacío para JPA
    public Factura() {
        // Por defecto, usa la estrategia de precio normal
        this.estrategiaCalculo = new PrecioNormalStrategy();
    }

    public Factura(Reserva reserva, int cantidadDias, BigDecimal precioNoche) {
        this.fechaEmision = LocalDate.now();
        this.reserva = reserva;
        // Por defecto usa precio normal, pero se puede cambiar con el setter
        this.estrategiaCalculo = new PrecioNormalStrategy();
        this.total = registrarTotalFactura(precioNoche, cantidadDias);
    }

    // 🌟 Método que delega el cálculo a la estrategia seteada dinámicamente
    public BigDecimal registrarTotalFactura(BigDecimal precioNoche, int cantidadDias) {
        return this.estrategiaCalculo.calcularTotal(precioNoche, cantidadDias);
    }

    // Setter para poder cambiar de estrategia en tiempo de ejecución
    public void setEstrategiaCalculo(CalculoEstadiaStrategy estrategiaCalculo) {
        this.estrategiaCalculo = estrategiaCalculo;
    }

    // Getters y Setters tradicionales
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id") // Crea una clave foránea en la tabla de medios_pago
    private java.util.List<MedioPago> pagos = new java.util.ArrayList<>();

    public java.util.List<MedioPago> getPagos() { return pagos; }
    public void setPagos(java.util.List<MedioPago> pagos) { this.pagos = pagos; }
    
    public void agregarPago(MedioPago pago) {
        this.pagos.add(pago);
    }
}