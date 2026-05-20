package isi.deso.tpsolo.decoradores;

import java.math.BigDecimal;

public class CocheraDecorator extends HabitacionDecorator {

    public CocheraDecorator(ComponenteHabitacion habitacionDecorada) {
        super(habitacionDecorada);
    }

    @Override
    public BigDecimal getPrecioNoche() {
        // Le suma $5.000 fijos al costo por noche de la habitación
        return super.getPrecioNoche().add(new BigDecimal("5000"));
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Adicional Cochera";
    }
}