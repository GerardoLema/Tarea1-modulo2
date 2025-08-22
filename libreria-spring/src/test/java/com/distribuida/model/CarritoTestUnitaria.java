package com.distribuida.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class CarritoTestUnitaria {
    private Carrito carrito;
    private CarritoItem item1;
    private CarritoItem item2;
    @BeforeEach
    void setUp() {
        carrito = new Carrito();
        // Item 1: 2 unidades de 10.00
        item1 = new CarritoItem();
        item1.setCantidad(2);
        item1.setPrecioUnitario(BigDecimal.valueOf(10.00));
        item1.calcTotal();
        item1.setCarrito(carrito);
        // Item 2: 1 unidad de 20.00
        item2 = new CarritoItem();
        item2.setCantidad(1);
        item2.setPrecioUnitario(BigDecimal.valueOf(20.00));
        item2.calcTotal();
        item2.setCarrito(carrito);
        carrito.getItems().add(item1);
        carrito.getItems().add(item2);
    }
    @Test
    void testRecomputarTotales_sinDescuento() {
        BigDecimal tasaIva = BigDecimal.valueOf(0.12); // IVA 12%
        carrito.recomputarTotales(tasaIva);
        assertEquals(BigDecimal.valueOf(40.00).setScale(2), carrito.getSubtotal(), "El subtotal debe ser 40.00");
        assertEquals(BigDecimal.valueOf(4.80).setScale(2), carrito.getImpuestos(), "El IVA debe ser 4.80");
        assertEquals(BigDecimal.valueOf(44.80).setScale(2), carrito.getTotal(), "El total debe ser 44.80");
    }
    @Test
    void testRecomputarTotales_conDescuento() {
        carrito.setDescuento(BigDecimal.valueOf(5.00));
        BigDecimal tasaIva = BigDecimal.valueOf(0.12); // IVA 12%
        carrito.recomputarTotales(tasaIva);
        assertEquals(BigDecimal.valueOf(35.00).setScale(2), carrito.getSubtotal().subtract(carrito.getDescuento()), "Base imponible debe ser 35.00");
        assertEquals(BigDecimal.valueOf(4.20).setScale(2), carrito.getImpuestos(), "El IVA debe ser 4.20");
        assertEquals(BigDecimal.valueOf(39.20).setScale(2), carrito.getTotal(), "El total debe ser 39.20");
    }
    @Test
    void testRecomprobacionTotalesCompat_noNulls() {
        carrito.setSubtotal(null);
        carrito.setDescuento(null);
        carrito.setImpuestos(null);
        carrito.setTotal(null);
        carrito.recomprobacionTotalesCompat();
        assertNotNull(carrito.getSubtotal(), "Subtotal no debe ser null");
        assertNotNull(carrito.getDescuento(), "Descuento no debe ser null");
        assertNotNull(carrito.getImpuestos(), "Impuestos no debe ser null");
        assertNotNull(carrito.getTotal(), "Total no debe ser null");
    }
}
