package isi.deso.tpsolo.strategy;

import java.math.BigDecimal;

public class DescuentoEstadiaLargaStrategy implements CalculoEstadiaStrategy {
    @Override
    public BigDecimal calcularTotal(BigDecimal precioNoche, int cantidadDias) {
        BigDecimal totalSinDescuento = precioNoche.multiply(new BigDecimal(cantidadDias));
        
        // Si la estadía es de más de 5 días, se le aplica un 10% de descuento
        if (cantidadDias > 5) {
            // Multiplicar por 0.90 equivale a restar el 10%
            return totalSinDescuento.multiply(new BigDecimal("0.90"));
        }
        return totalSinDescuento;
    }
}