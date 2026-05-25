package isi.deso.tpsolo.entidades;
 
import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
@Entity
@Table(name = "facturas")
public class Factura {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id", nullable = true)
    @JsonIgnoreProperties({"huesped", "habitacion", "reservas", "hibernateLazyInitializer", "handler"})
    private Reserva reserva;
 
    @Column(name = "reserva_id_snapshot")
    private Long reservaIdSnapshot;
 
    @Column(name = "huesped_dni")
    private String huespedDni;
 
    @Column(name = "huesped_nombre")
    private String huespedNombre;
 
    @Column(name = "monto_base")
    private Double montoBase;
 
    @Column(name = "adicional_cochera", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean adicionalCochera = false;
 
    @Column(name = "adicional_frigobar", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean adicionalFrigobar = false;
 
    @Column(name = "monto_adicionales")
    private Double montoAdicionales;
 
    @Column(name = "descuento_aplicado")
    private Double descuentoAplicado;
 
    @Column(name = "monto_total")
    private Double montoTotal;
 
    @Column(name = "forma_pago")
    private String formaPago;
 
    @Column(name = "tarjeta_numero")
    private String tarjetaNumero;
 
    @Column(name = "tarjeta_banco")
    private String tarjetaBanco;
 
    @Column(name = "tarjeta_cuotas")
    private Integer tarjetaCuotas;
 
    @Column(name = "cheque_numero")
    private String chequeNumero;
 
    @Column(name = "cheque_banco")
    private String chequeBanco;
 
    @Column(name = "cheque_vencimiento")
    private String chequeVencimiento;
 
    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;
 
    public Factura() {}
 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }
 
    public Long getReservaIdSnapshot() { return reservaIdSnapshot; }
    public void setReservaIdSnapshot(Long reservaIdSnapshot) { this.reservaIdSnapshot = reservaIdSnapshot; }
 
    public String getHuespedDni() { return huespedDni; }
    public void setHuespedDni(String huespedDni) { this.huespedDni = huespedDni; }
 
    public String getHuespedNombre() { return huespedNombre; }
    public void setHuespedNombre(String huespedNombre) { this.huespedNombre = huespedNombre; }
 
    public Double getMontoBase() { return montoBase; }
    public void setMontoBase(Double montoBase) { this.montoBase = montoBase; }
 
    public Boolean isAdicionalCochera() { return adicionalCochera != null && adicionalCochera; }
    public void setAdicionalCochera(Boolean adicionalCochera) { this.adicionalCochera = adicionalCochera != null ? adicionalCochera : false; }
 
    public Boolean isAdicionalFrigobar() { return adicionalFrigobar != null && adicionalFrigobar; }
    public void setAdicionalFrigobar(Boolean adicionalFrigobar) { this.adicionalFrigobar = adicionalFrigobar != null ? adicionalFrigobar : false; }
 
    public Double getMontoAdicionales() { return montoAdicionales; }
    public void setMontoAdicionales(Double montoAdicionales) { this.montoAdicionales = montoAdicionales; }
 
    public Double getDescuentoAplicado() { return descuentoAplicado; }
    public void setDescuentoAplicado(Double descuentoAplicado) { this.descuentoAplicado = descuentoAplicado; }
 
    public Double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(Double montoTotal) { this.montoTotal = montoTotal; }
 
    public String getFormaPago() { return formaPago; }
    public void setFormaPago(String formaPago) { this.formaPago = formaPago; }
 
    public String getTarjetaNumero() { return tarjetaNumero; }
    public void setTarjetaNumero(String tarjetaNumero) { this.tarjetaNumero = tarjetaNumero; }
 
    public String getTarjetaBanco() { return tarjetaBanco; }
    public void setTarjetaBanco(String tarjetaBanco) { this.tarjetaBanco = tarjetaBanco; }
 
    public Integer getTarjetaCuotas() { return tarjetaCuotas; }
    public void setTarjetaCuotas(Integer tarjetaCuotas) { this.tarjetaCuotas = tarjetaCuotas; }
 
    public String getChequeNumero() { return chequeNumero; }
    public void setChequeNumero(String chequeNumero) { this.chequeNumero = chequeNumero; }
 
    public String getChequeBanco() { return chequeBanco; }
    public void setChequeBanco(String chequeBanco) { this.chequeBanco = chequeBanco; }
 
    public Integer getCuotas() { return tarjetaCuotas; }
 
    public String getChequeVencimiento() { return chequeVencimiento; }
    public void setChequeVencimiento(String chequeVencimiento) { this.chequeVencimiento = chequeVencimiento; }
 
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }
}