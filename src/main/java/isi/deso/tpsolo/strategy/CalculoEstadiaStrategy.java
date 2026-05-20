package isi.deso.tpsolo.strategy;

import java.math.BigDecimal;

public interface CalculoEstadiaStrategy {
    // Recibe el precio base por noche y la cantidad de días para calcular el total
    BigDecimal calcularTotal(BigDecimal precioNoche, int cantidadDias);
}