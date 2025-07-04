package com.distribuida.controller;
import com.distribuida.model.Categoria;
import com.distribuida.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SuppressWarnings("removal")
@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoriaService categoriaService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testFindAll() throws Exception {
        Categoria categoria = new Categoria(1,"Fisica", "Fisica Basica Elemental");
        Mockito.when(categoriaService.findAll()).thenReturn(List.of(categoria));
        mockMvc.perform(get("/api/Categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"));}
    @Test
    public void testPostCategoria() throws Exception {
        Categoria categoria = new Categoria(1,"Fisica", "Fisica Basica Elemental");
        Mockito.when(categoriaService.save(any(Categoria.class))).thenReturn(categoria);
        mockMvc.perform(post("api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoria").value("Fisica"));}
    @Test
    public void testDeleteCategoria() throws Exception {
        mockMvc.perform(delete("/api/categorias/1"))
                .andExpect(status().isNoContent()); }}