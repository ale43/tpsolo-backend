package isi.deso.tpsolo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "pagos_tarjeta")
public class Tarjeta extends MedioPago {

    private String datosTransaccion; // Para guardar el cupón o nro de transacción

    public Tarjeta() {
        super();
    }

    public Tarjeta(BigDecimal monto, String datosTransaccion) {
        super(monto);
        this.datosTransaccion = datosTransaccion;
    }

    @Override
    public void procesarPago() {
        System.out.println(">> Conectando con pasarela de tarjetas. Cobrando: $" + getMonto());
    }

    public String getDatosTransaccion() { return datosTransaccion; }
    public void setDatosTransaccion(String datosTransaccion) { this.datosTransaccion = datosTransaccion; }
}