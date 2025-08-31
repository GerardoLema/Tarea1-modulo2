import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class CarritoItemTestUnitaria {
    private CarritoItem carritoItem;
    private Libro libro;
    private Carrito carrito;
    @BeforeEach
    public void setUp() {
        carritoItem = new CarritoItem();
        libro = new Libro();
        carrito = new Carrito();
    }
    @Test
    public void testCalcTotal() {
               int cantidad = 3;
        BigDecimal precioUnitario = new BigDecimal("15.50");
        BigDecimal expectedTotal = new BigDecimal("46.50");
        carritoItem.setCantidad(cantidad);
        carritoItem.setPrecioUnitario(precioUnitario);
        carritoItem.calcTotal();
               assertNotNull(carritoItem.getTotal());
        assertEquals(expectedTotal, carritoItem.getTotal());
    }
    @Test
    public void testCalcTotalWithZeroQuantity() {
        int cantidad = 0;
        BigDecimal precioUnitario = new BigDecimal("15.50");
        BigDecimal expectedTotal = BigDecimal.ZERO.setScale(2);

        carritoItem.setCantidad(cantidad);
        carritoItem.setPrecioUnitario(precioUnitario);
        carritoItem.calcTotal();
        assertNotNull(carritoItem.getTotal());
        assertEquals(expectedTotal, carritoItem.getTotal());
    }
    @Test
    public void testCalcTotalWithNullValues() {
        BigDecimal expectedTotal = BigDecimal.ZERO.setScale(2);
        carritoItem.setCantidad(null);
        carritoItem.setPrecioUnitario(null);
        carritoItem.calcTotal();
        assertNotNull(carritoItem.getTotal());
        assertEquals(expectedTotal, carritoItem.getTotal());
    }
    @Test
    public void testSettersAndGetters() {
        Long id = 1L;
        int cantidad = 5;
        BigDecimal precio = new BigDecimal("25.00");
        carritoItem.setIdCarritoItem(id);
        carritoItem.setCarrito(carrito);
        carritoItem.setLibro(libro);
        carritoItem.setCantidad(cantidad);
        carritoItem.setPrecioUnitario(precio);
        assertEquals(id, carritoItem.getIdCarritoItem());
        assertEquals(carrito, carritoItem.getCarrito());
        assertEquals(libro, carritoItem.getLibro());
        assertEquals(cantidad, carritoItem.getCantidad());
        assertEquals(precio, carritoItem.getPrecioUnitario());
    }
}