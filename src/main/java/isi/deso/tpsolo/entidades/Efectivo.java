package isi.deso.tpsolo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "pagos_efectivo")
public class Efectivo extends MedioPago {

    public Efectivo() {
        super();
    }

    public Efectivo(BigDecimal monto) {
        super(monto);
    }

    @Override
    public void procesarPago() {
        System.out.println(">> Procesando pago en efectivo por: $" + getMonto());
        // Acá iría lógica específica de caja si fuera necesario
    }
}