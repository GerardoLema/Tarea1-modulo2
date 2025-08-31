package com.distribuida.controller;

import com.distribuida.model.Carrito;
import com.distribuida.service.CarritoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarritoGuestController.class)
@DisplayName("Pruebas Unitarias para CarritoGuestController")
class CarritoGuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService carritoService;

    // Métodos utilitarios para generar objetos de prueba
    private Carrito createMockCarrito() {
        Carrito carrito = new Carrito();
        carrito.setToken("test-token");
        carrito.setSubtotal(BigDecimal.valueOf(100.00));
        return carrito;
    }

    // --- Pruebas para los endpoints ---

    @Test
    @DisplayName("Debe crear o recuperar un carrito por token")
    void shouldCreateOrGetCarritoByToken() throws Exception {
        Carrito mockCarrito = createMockCarrito();
        when(carritoService.getOrCreateByToken("test-token")).thenReturn(mockCarrito);

        mockMvc.perform(post("/api/guest/cart")
                        .param("token", "test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test-token"))
                .andExpect(jsonPath("$.subtotal").value(100.00));

        verify(carritoService, times(1)).getOrCreateByToken("test-token");
    }

    @Test
    @DisplayName("Debe obtener un carrito existente por token")
    void shouldGetCarritoByToken() throws Exception {
        Carrito mockCarrito = createMockCarrito();
        when(carritoService.getByToken("test-token")).thenReturn(mockCarrito);

        mockMvc.perform(get("/api/guest/cart")
                        .param("token", "test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test-token"));

        verify(carritoService, times(1)).getByToken("test-token");
    }

    @Test
    @DisplayName("Debe agregar un ítem al carrito")
    void shouldAddItemToCarrito() throws Exception {
        Carrito mockCarrito = createMockCarrito();
        when(carritoService.addItem("test-token", 1, 2)).thenReturn(mockCarrito);

        String jsonBody = "{\"libroId\": 1, \"cantidad\": 2}";

        mockMvc.perform(post("/api/guest/cart/items")
                        .param("token", "test-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test-token"));

        verify(carritoService, times(1)).addItem("test-token", 1, 2);
    }

    @Test
    @DisplayName("Debe actualizar la cantidad de un ítem en el carrito")
    void shouldUpdateItemQuantity() throws Exception {
        Carrito mockCarrito = createMockCarrito();
        when(carritoService.updateItemCantidad("test-token", 101L, 5)).thenReturn(mockCarrito);

        String jsonBody = "{\"cantidad\": 5}";

        mockMvc.perform(put("/api/guest/cart/items/{carritoItemId}", 101L)
                        .param("token", "test-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test-token"));

        verify(carritoService, times(1)).updateItemCantidad("test-token", 101L, 5);
    }

    @Test
    @DisplayName("Debe eliminar un ítem del carrito")
    void shouldRemoveItemFromCarrito() throws Exception {
        doNothing().when(carritoService).removeItem("test-token", 101L);

        mockMvc.perform(delete("/api/guest/cart/items/{carritoItemId}", 101L)
                        .param("token", "test-token"))
                .andExpect(status().isNoContent());

        verify(carritoService, times(1)).removeItem("test-token", 101L);
    }

    @Test
    @DisplayName("Debe vaciar el carrito")
    void shouldClearCarrito() throws Exception {
        doNothing().when(carritoService).clearByToken("test-token");

        mockMvc.perform(delete("/api/guest/cart/clear")
                        .param("token", "test-token"))
                .andExpect(status().isNoContent());

        verify(carritoService, times(1)).clearByToken("test-token");
    }
}