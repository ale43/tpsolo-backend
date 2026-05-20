package isi.deso.tpsolo.strategy;

import java.math.BigDecimal;

public class PrecioNormalStrategy implements CalculoEstadiaStrategy {
    @Override
    public BigDecimal calcularTotal(BigDecimal precioNoche, int cantidadDias) {
        return precioNoche.multiply(new BigDecimal(cantidadDias));
    }
}