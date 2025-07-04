package com.distribuida.controller;
import com.distribuida.model.*;
import com.distribuida.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SuppressWarnings("removal")
@WebMvcTest(FacturaDetalleController.class)
public class FacturaDetalleControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacturaDetalleService facturaDetalleService;
    @MockBean
    private LibroService libroService;
    private FacturaService facturaService;
    private AutorService autorService;
    private CategoriaService categoriaService;
    private  FacturaDetalle facturaDetalle;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testFindAll() throws Exception {
        Cliente cliente = new Cliente(1,"1701234567", "Juan", "Taipe", "Av. por ahi","0984567891","gle23@gmail.com");
        Factura factura = new Factura(1,"FAC-0001", new Date(), 100.00, 15.00, 115.00, cliente );
        Autor autor = new Autor(1,"Gerardo", "Lopez", "Argentina", "Via principal","0984567891","gle23@gmail.com");
        Categoria categoria = new Categoria(1,"Fisica", "Fisica Basica Elemental");
        Libro libro = new Libro(1, "Spring in Action", "Manning", 200, "4th", "Español", new Date(),"Programación distribuida que usa patrones ","Pasta blanda", " 978-1617291208", 20, "Blanco", "Virtual",20.00, categoria, autor);
        FacturaDetalle facturaDetalle = new FacturaDetalle(1, 2,100.00,factura,libro);
        Mockito.when(facturaDetalleService.findAll()).thenReturn(List.of(facturaDetalle));
        mockMvc.perform(get("/api/facturaDetalles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cantidad").value(2));}
    @Test
    public void testPostFacturaDetalle() throws Exception {
        Cliente cliente = new Cliente(1,"1701234567", "Juan", "Taipe", "Av. por ahi","0984567891","gle23@gmail.com");
        Factura factura = new Factura(1,"FAC-0001", new Date(), 100.00, 15.00, 115.00, cliente );
        Autor autor = new Autor(1,"Gerardo", "Lopez", "Argentina", "Via principal","0984567891","gle23@gmail.com");
        Categoria categoria = new Categoria(1,"Fisica", "Fisica Basica Elemental");
        Libro libro = new Libro(1, "Spring in Action", "Manning", 200, "4th", "Español", new Date(),"Programación distribuida que usa patrones ","Pasta blanda", " 978-1617291208", 20, "Blanco", "Virtual",20.00, categoria, autor);
        FacturaDetalle facturaDetalle = new FacturaDetalle(1, 2,100.00,factura,libro);
        Mockito.when(facturaDetalleService.save(any(FacturaDetalle.class))).thenReturn(facturaDetalle);
        mockMvc.perform(post("api/facturaDetalles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(facturaDetalle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(2));}
    @Test
    public void testDeleteFacturaDetalle() throws Exception {
        mockMvc.perform(delete("/api/facturaDetalles/1"))
                .andExpect(status().isNoContent()); }}