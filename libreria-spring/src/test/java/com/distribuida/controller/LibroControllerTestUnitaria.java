package com.distribuida.controller;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class LibroControllerTestUnitaria {
    @InjectMocks
    private LibroController libroController;
    @Mock
    private LibroService libroService;
    private Libro libro;
    private Autor autor;
    private Categoria Categoria;
    @BeforeEach
    public  void setup(){
        MockitoAnnotations.openMocks(this);
        Categoria categoria = new Categoria(1,"Fisica","Fisica Basica Elemenetal");
        Autor autor = new Autor(1,"Gerardo", "Lopez", "Argentina","Via Principal", "0984561237","glem23@hotmail.com");
        libro= new Libro();
        libro.setIdlibro(1);
        libro.setTitulo("Spring in Action");
        libro.setEditorial("Manning");
        libro.setNumpaginas(200);
        libro.setEdicion("4th");
        libro.setIdioma("Español");
        libro.setFechapublicacion(new Date());
        libro.setDescripcion("Programación distribuida que usa patrones ");
        libro.setTipopasta("Pasta blanda");
        libro.setiSBN(" 978-1617291208");
        libro.setNumejemplares(20);
        libro.setPortada("Blanco");
        libro.setPresentacion("Virtual");
        libro.setPrecio(20.00);
        libro.setCategoria(categoria);
        libro.setAutor(autor);}
    @Test
    public void testFindAll(){
        when(libroService.findAll()).thenReturn(List.of(libro));
        ResponseEntity<List<Libro>> respuesta = libroController.fibdAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1,respuesta.getBody().size());
        verify(libroService, times(1)).findAll(); }
    @Test
    public void testFindOneExistente(){
        when(libroService.findOne(1)).thenReturn(libro);
        ResponseEntity<Libro> respuesta= libroController.findOne(1);
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(libro.getTitulo(),respuesta.getBody());    }
    @Test
    public void testFindOneNoExistente() {
        when(libroService.findOne(2)).thenReturn(null);
        ResponseEntity<Libro> respuesta = libroController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());  }
    @Test
    public void testSave(){
        when(libroService.save(libro)).thenReturn(libro);
        ResponseEntity<Libro> respuesta= libroController.save(libro);
        assertEquals(200, respuesta.getBody()); }
    @Test
    public void  testUpdateExistente(){
        when(libroService.update(1, libro)).thenReturn(libro);
        ResponseEntity<Libro>respuesta= libroController.update(1,libro);
        assertEquals(200,respuesta.getStatusCodeValue());}
    @Test
    public void testUpdateNoExistente(){
        when(libroService.update(eq(2),any(Libro.class))).thenReturn(null);
        ResponseEntity<Libro>respuesta = libroController.update(2, libro);
        assertEquals(404, respuesta.getStatusCodeValue()); }
    @Test
    public void testDelete(){
        doNothing().when(libroService).delete(1);
        ResponseEntity<Void> respuesta = libroController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(libroService, times(1)).delete(1);}}
