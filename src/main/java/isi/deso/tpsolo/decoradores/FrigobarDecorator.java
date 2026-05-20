package isi.deso.tpsolo.decoradores;

import java.math.BigDecimal;

public class FrigobarDecorator extends HabitacionDecorator {

    public FrigobarDecorator(ComponenteHabitacion habitacionDecorada) {
        super(habitacionDecorada);
    }

    @Override
    public BigDecimal getPrecioNoche() {
        // Le suma $3.500 de consumos acumulados
        return super.getPrecioNoche().add(new BigDecimal("3500"));
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Consumos Frigobar";
    }
}