import isi.deso.tpsolo.entidades.*;
import java.math.BigDecimal;

public class MedioPagoServicio {

    // 🌟 EL MÉTODO FACTORY
    public static MedioPago crearMedioPago(String tipo, BigDecimal monto, String... datosExtra) {
        if (tipo == null) {
            return null;
        }

        switch (tipo.toUpperCase()) {
            case "EFECTIVO":
                return new Efectivo(monto);
                
            case "TARJETA":
                // Si pasaron datos del cupón los guarda, sino manda vacío
                String transaccion = (datosExtra.length > 0) ? datosExtra[0] : "Ref: Sin datos";
                return new Tarjeta(monto, transaccion);
                
            case "CHEQUE":
                // Requiere nro de cheque y banco
                String nroCheque = (datosExtra.length > 0) ? datosExtra[0] : "000000";
                String banco = (datosExtra.length > 1) ? datosExtra[1] : "Banco Genérico";
                return new Cheque(monto, nroCheque, banco);
                
            default:
                throw new IllegalArgumentException("El medio de pago '" + tipo + "' no es válido.");
        }
    }
}