package com.distribuida.controller;

import com.distribuida.dao.CarritoItemRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Libro;
import com.distribuida.service.CarritoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Pruebas de Integración para CarritoGuestController")
class CarritoGuestControllerTestIntegracion
{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private CarritoItemRepository carritoItemRepository;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private ObjectMapper objectMapper;
    private final String API_BASE_URL = "/api/guest/cart";
    private final String TEST_TOKEN = "token-de-integracion";
    private Libro testLibro;
    @BeforeEach
    void setUp() {
        // Limpiar la base de datos antes de cada prueba para asegurar un estado limpio
        carritoItemRepository.deleteAll();
        libroRepository.deleteAll();
        // Crear un libro en la base de datos de prueba
        testLibro = new Libro();
        testLibro.setTitulo("El arte de la guerra");
        testLibro.setPrecio(15.00);
        testLibro.setiSBN("0985647897");
        testLibro.setNumEjemplares(10);
        testLibro.setDescripcion("Una descripción de prueba");
        testLibro.setEditorial("Editorial de prueba");
        testLibro.setNumPaginas(250);
        // Conversión de LocalDate a Date utilizando ZonedDateTime para evitar el error de tipos incompatibles
        LocalDate localDate = LocalDate.of(2023, 1, 1);
        testLibro.setFechaPublicacion(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        libroRepository.save(testLibro);
    }

    @Test
    @DisplayName("POST / - Debe crear un nuevo carrito para el token")
    void shouldCreateNewCarrito() throws Exception {
        mockMvc.perform(post(API_BASE_URL)
                        .param("token", TEST_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(TEST_TOKEN));

        // Verificación adicional: el servicio realmente creó un carrito
        var carrito = carritoService.getByToken(TEST_TOKEN);
        assert(carrito != null && carrito.getToken().equals(TEST_TOKEN));
    }

    @Test
    @DisplayName("POST /items - Debe agregar un ítem y actualizar el carrito")
    void shouldAddItemAndRecalculateTotals() throws Exception {
        // Primero, creamos un carrito para el token
        carritoService.getOrCreateByToken(TEST_TOKEN);

        Map<String, Integer> requestBody = Map.of("libroId", testLibro.getIdlibro(), "cantidad", 2);
        String jsonContent = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(post(API_BASE_URL + "/items")
                        .param("token", TEST_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()").value(1))
                .andExpect(jsonPath("$.subtotal").value(30.00));

        // Verificación adicional: el ítem fue persistido en la base de datos
        var carrito = carritoService.getByToken(TEST_TOKEN);
        assert(carrito.getItems().size() == 1);
        assert(carrito.getItems().iterator().next().getCantidad() == 2);
    }

    @Test
    @DisplayName("PUT /items/{id} - Debe actualizar la cantidad de un ítem")
    void shouldUpdateItemQuantity() throws Exception {
        // Pre-poblar el carrito con un ítem
        var carrito = carritoService.addItem(TEST_TOKEN, testLibro.getIdlibro(), 1);
        var carritoItemId = carrito.getItems().iterator().next().getIdCarritoItem();

        Map<String, Integer> requestBody = Map.of("cantidad", 3);
        String jsonContent = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(put(API_BASE_URL + "/items/{carritoItemId}", carritoItemId)
                        .param("token", TEST_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subtotal").value(45.00)); // 15.00 * 3

        // Verificación adicional: el cambio se refleja en la base de datos
        var updatedItem = carritoItemRepository.findById(carritoItemId).orElseThrow();
        assert(updatedItem.getCantidad() == 3);
    }

    @Test
    @DisplayName("DELETE /items/{id} - Debe eliminar un ítem del carrito")
    void shouldRemoveItemFromCarrito() throws Exception {
        var carrito = carritoService.addItem(TEST_TOKEN, testLibro.getIdlibro(), 1);
        var carritoItemId = carrito.getItems().iterator().next().getIdCarritoItem();

        mockMvc.perform(delete(API_BASE_URL + "/items/{carritoItemId}", carritoItemId)
                        .param("token", TEST_TOKEN))
                .andExpect(status().isNoContent());

        // Verificación adicional: el ítem ya no existe en la base de datos
        assert(carritoItemRepository.findById(carritoItemId).isEmpty());
    }

    @Test
    @DisplayName("DELETE /clear - Debe vaciar el carrito por completo")
    void shouldClearCarrito() throws Exception {
        carritoService.addItem(TEST_TOKEN, testLibro.getIdlibro(), 1);

        mockMvc.perform(delete(API_BASE_URL + "/clear")
                        .param("token", TEST_TOKEN))
                .andExpect(status().isNoContent());

        // Verificación adicional: el carrito está vacío en la base de datos
        var carrito = carritoService.getByToken(TEST_TOKEN);
        assert(carrito.getItems().isEmpty());
    }
}
