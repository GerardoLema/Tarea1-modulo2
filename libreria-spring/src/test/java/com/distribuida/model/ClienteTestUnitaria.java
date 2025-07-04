package com.distribuida.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ClienteTestUnitaria {

    private Cliente cliente;
    @BeforeEach
    public void setUp() {cliente = new Cliente(1, "0458978956", "Juan", "Lopez", "Av. Unidad Nacional", "02457862", "defr23@gmail.com");}
    @Test
    public void testClienteConstructorAndGetteres() {
        assertAll("validar datos de cliente",
                () -> assertEquals(1, cliente.getIdCliente()),
                () -> assertEquals("0458978956", cliente.getCedula()),
                () -> assertEquals("Juan", cliente.getNombre()),
                () -> assertEquals("Lopez", cliente.getApellido()),
                () -> assertEquals("Av. Unidad Nacional", cliente.getDireccion()),
                () -> assertEquals("02457862", cliente.getTelefono()),
                () -> assertEquals("defr23@gmail.com", cliente.getCorreo())); }
    @Test
    public void testClienteSettrs() {
        cliente = new Cliente();
        cliente.setIdCliente(2);
        cliente.setCedula("1745867815");
        cliente.setNombre("Gerardo");
        cliente.setApellido("Alcarcon");
        cliente.setDireccion("Calisto Pino");
        cliente.setTelefono("0984567891");
        cliente.setCorreo("atp@hotmail.com");
        assertAll("validar datos de cliente",
                () -> assertEquals(2, cliente.getIdCliente()),
                () -> assertEquals("1745867815", cliente.getCedula()),
                () -> assertEquals("Gerardo", cliente.getNombre()),
                () -> assertEquals("Alcarcon", cliente.getApellido()),
                () -> assertEquals("Calisto Pino", cliente.getDireccion()),
                () -> assertEquals("0984567891", cliente.getTelefono()));}
    @Test
    public void testClienteToString(){
        String str = cliente.toString();
        assertAll("validar datos de cliente",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("0458978956")),
                () -> assertTrue(str.contains("Juan")),
                () -> assertTrue(str.contains("Lopez")),
                () -> assertTrue(str.contains("Av. Unidad Nacional")),
                () -> assertTrue(str.contains("02457862")),
                () -> assertTrue(str.contains("defr23@gmail.com")));}
}