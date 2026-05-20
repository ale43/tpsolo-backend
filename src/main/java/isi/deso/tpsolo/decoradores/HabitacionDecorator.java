package isi.deso.tpsolo.decoradores;

import java.math.BigDecimal;

public abstract class HabitacionDecorator implements ComponenteHabitacion {
    protected ComponenteHabitacion habitacionDecorada;

    public HabitacionDecorator(ComponenteHabitacion habitacionDecorada) {
        this.habitacionDecorada = habitacionDecorada;
    }

    @Override
    public BigDecimal getPrecioNoche() {
        return habitacionDecorada.getPrecioNoche();
    }

    @Override
    public String getDescripcion() {
        return habitacionDecorada.getDescripcion();
    }
}