package isi.deso.tpsolo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "pagos_cheque")
public class Cheque extends MedioPago {

    private String numeroCheque;
    private String banco;

    public Cheque() {
        super();
    }

    public Cheque(BigDecimal monto, String numeroCheque, String banco) {
        super(monto);
        this.numeroCheque = numeroCheque;
        this.banco = banco;
    }

    @Override
    public void procesarPago() {
        System.out.println(">> Registrando Cheque Nro " + numeroCheque + " del Banco " + banco + " en cartera.");
    }

    // Getters y Setters
    public String getNumeroCheque() { return numeroCheque; }
    public void setNumeroCheque(String numeroCheque) { this.numeroCheque = numeroCheque; }

    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }
}