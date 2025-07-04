package com.distribuida.controller;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Cliente;
import com.distribuida.model.Libro;
import com.distribuida.service.AutorService;
import com.distribuida.service.CategoriaService;
import com.distribuida.service.ClienteService;
import com.distribuida.service.LibroService;
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
@WebMvcTest(LibroController.class)
public class LibroControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LibroService libroService;
    private AutorService autorService;
    private CategoriaService categoriaService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testFindAll() throws Exception {
        Autor autor = new Autor(1,"Gerardo", "Lopez", "Argentina", "Via principal","0984567891","gle23@gmail.com");
        Categoria categoria = new Categoria(1,"Fisica", "Fisica Basica Elemental");
        Libro libro = new Libro(1, "Spring in Action", "Manning", 200, "4th", "Español", new Date(),"Programación distribuida que usa patrones ","Pasta blanda", " 978-1617291208", 20, "Blanco", "Virtual",20.00, categoria, autor);
        Mockito.when(libroService.findAll()).thenReturn(List.of(libro));
        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Titulo").value("Spring in Action"));}
    @Test
    public void testPostLibro() throws Exception {
        Autor autor = new Autor(1,"Gerardo", "Lopez", "Argentina", "Via principal","0984567891","gle23@gmail.com");
        Categoria categoria = new Categoria(1,"Fisica", "Fisica Basica Elemental");
        Libro libro = new Libro(1, "Spring in Action", "Manning", 200, "4th", "Español", new Date(),"Programación distribuida que usa patrones ","Pasta blanda", " 978-1617291208", 20, "Blanco", "Virtual",20.00, categoria, autor);

        Mockito.when(libroService.save(any(Libro.class))).thenReturn(libro);
        mockMvc.perform(post("api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(libro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Titulo").value("Spring in Action"));}
    @Test
    public void testDeleteLibro() throws Exception {
        mockMvc.perform(delete("/api/libros/1"))
                .andExpect(status().isNoContent()); }}