package isi.deso.tpsolo.servicios;
 
import isi.deso.tpsolo.entidades.Factura;
import isi.deso.tpsolo.entidades.Reserva;
import isi.deso.tpsolo.repositorio.FacturaRepositorio;
import isi.deso.tpsolo.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
 
@Service
public class FacturaServicio {
 
    private static final double PRECIO_COCHERA = 25000.0;
    private static final double PRECIO_FRIGOBAR = 35000.0;
    private static final double PRECIO_NOCHE_BASE = 30000.0;
    private static final double DESCUENTO_EFECTIVO = 0.10;
 
    @Autowired
    private FacturaRepositorio facturaRepositorio;
 
    @Autowired
    private ReservaRepositorio reservaRepositorio;
 
    public Factura generarFactura(Map<String, Object> payload) {
        Long reservaId = Long.valueOf(payload.get("reservaId").toString());
        Reserva reserva = reservaRepositorio.findById(reservaId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con ID: " + reservaId));
 
        long noches = ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
        double montoBase = noches * PRECIO_NOCHE_BASE;
 
        boolean aplicarCochera = Boolean.parseBoolean(payload.get("aplicarCochera").toString());
        boolean aplicarFrigobar = Boolean.parseBoolean(payload.get("aplicarFrigobar").toString());
 
        double montoAdicionales = 0.0;
        if (aplicarCochera) montoAdicionales += PRECIO_COCHERA;
        if (aplicarFrigobar) montoAdicionales += PRECIO_FRIGOBAR;
 
        double subtotal = montoBase + montoAdicionales;
 
        String formaPago = payload.get("formaPago").toString();
        double descuentoAplicado = 0.0;
        double montoTotal = subtotal;
 
        if ("EFECTIVO".equals(formaPago)) {
            descuentoAplicado = DESCUENTO_EFECTIVO * 100;
            montoTotal = subtotal * (1 - DESCUENTO_EFECTIVO);
        }
 
        Map<String, Object> detalles = (Map<String, Object>) payload.get("detallesPago");
 
        Factura factura = new Factura();
        factura.setReserva(reserva);
        factura.setReservaIdSnapshot(reserva.getId());
        factura.setHuespedDni(reserva.getHuesped().getDni());
        factura.setHuespedNombre(reserva.getHuesped().getApellido() + ", " + reserva.getHuesped().getNombre());
        factura.setMontoBase(montoBase);
        factura.setAdicionalCochera(aplicarCochera);
        factura.setAdicionalFrigobar(aplicarFrigobar);
        factura.setMontoAdicionales(montoAdicionales);
        factura.setDescuentoAplicado(descuentoAplicado);
        factura.setMontoTotal(montoTotal);
        factura.setFormaPago(formaPago);
        factura.setFechaEmision(LocalDateTime.now());
 
        if ("TARJETA".equals(formaPago) && detalles != null) {
            factura.setTarjetaNumero(detalles.get("tarjetaNumero") != null ? detalles.get("tarjetaNumero").toString() : null);
            factura.setTarjetaBanco(detalles.get("tarjetaBanco") != null ? detalles.get("tarjetaBanco").toString() : null);
            factura.setTarjetaCuotas(detalles.get("cuotas") != null ? Integer.valueOf(detalles.get("cuotas").toString()) : null);
        }
 
        if ("CHEQUE".equals(formaPago) && detalles != null) {
            factura.setChequeNumero(detalles.get("chequeNumero") != null ? detalles.get("chequeNumero").toString() : null);
            factura.setChequeBanco(detalles.get("chequeBanco") != null ? detalles.get("chequeBanco").toString() : null);
            factura.setChequeVencimiento(detalles.get("chequeVencimiento") != null ? detalles.get("chequeVencimiento").toString() : null);
        }
 
        reserva.setActiva(false);
        reservaRepositorio.save(reserva);
 
        return facturaRepositorio.save(factura);
    }
 
    public List<Factura> listarTodas() {
        return facturaRepositorio.findAll();
    }
 
    public List<Factura> listarPorHuesped(String dni) {
        return facturaRepositorio.findByHuespedDniOrderByFechaEmisionDesc(dni);
    }
 
    public List<Factura> listarPorReserva(Long reservaId) {
        return facturaRepositorio.findByReservaIdOrderByFechaEmisionDesc(reservaId);
    }
}