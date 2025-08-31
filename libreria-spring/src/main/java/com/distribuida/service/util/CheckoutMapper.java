package com.distribuida.service.util;

import com.distribuida.model.*;

import java.util.Date;

public class CheckoutMapper {
    private CheckoutMapper() { }

    public static Factura construirFacturaDesdeCarrito(Carrito carrito, String numFactura, double tasaIva) {
        Factura f = new Factura();
        f.setNumFactura(numFactura);
        f.setFecha(new Date());
        f.setCliente(carrito.getCliente());

        double subtotal = carrito.getItems().stream()
                .mapToDouble(i -> i.getTotal().doubleValue())
                .sum();

        double iva = Math.max(0, subtotal) * tasaIva;
        double total = subtotal + iva;

        f.setTotalNeto(subtotal);
        f.setIva(iva);
        f.setTotal(total);

        return f;
    }

    public static FacturaDetalle construirDetalle(Factura factura, CarritoItem carritoItem) {
        FacturaDetalle d = new FacturaDetalle();
        d.setFactura(factura);
        d.setLibro(carritoItem.getLibro());
        d.setCantidad(carritoItem.getCantidad());
        d.setSubtotal(carritoItem.getTotal().doubleValue());
        return d;
    }
}


