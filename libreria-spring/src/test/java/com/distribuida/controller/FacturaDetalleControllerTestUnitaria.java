package com.distribuida.controller;
import com.distribuida.model.*;
import com.distribuida.service.FacturaDetalleService;
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
public class FacturaDetalleControllerTestUnitaria {
    @InjectMocks
    private FacturaDetalleController facturaDetalleController;
    @Mock
    private FacturaDetalleService facturaDetalleService;
    private FacturaDetalle facturaDetalle;

    @BeforeEach
    public  void setup(){
        MockitoAnnotations.openMocks(this);
        Cliente cliente = new Cliente(1,"1701234567", "Juan", "Taipe", "Av. por ahi","0984567891","gle23@gmail.com");
        Factura factura = new Factura(1,"FAC-0001", new Date(), 100.00, 15.00, 115.00, cliente );
        Autor autor = new Autor(1,"Gerardo", "Lopez", "Argentina", "Via principal","0984567891","gle23@gmail.com");
        Categoria categoria = new Categoria(1,"Fisica", "Fisica Basica Elemental");
        Libro libro = new Libro(1, "Spring in Action", "Manning", 200, "4th", "Español", new Date(),"Programación distribuida que usa patrones ","Pasta blanda", " 978-1617291208", 20, "Blanco", "Virtual",20.00, categoria, autor);
        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(10);
        facturaDetalle.setSubtotal(20);
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);}
    @Test
    public void testFindAll(){
        when(facturaDetalleService.findAll()).thenReturn(List.of(facturaDetalle));
        ResponseEntity<List<FacturaDetalle>> respuesta = facturaDetalleController.fibdAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1,respuesta.getBody().size());
        verify(facturaDetalleService, times(1)).findAll(); }
    @Test
    public void testFindOneExistente(){
        when(facturaDetalleService.findOne(1)).thenReturn(facturaDetalle);
        ResponseEntity<FacturaDetalle> respuesta= facturaDetalleController.findOne(1);
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(facturaDetalle.getCantidad(),respuesta.getBody());    }
    @Test
    public void testFindOneNoExistente() {
        when(facturaDetalleService.findOne(2)).thenReturn(null);
        ResponseEntity<FacturaDetalle> respuesta = facturaDetalleController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());  }
    @Test
    public void testSave(){
        when(facturaDetalleService.save(facturaDetalle)).thenReturn(facturaDetalle);
        ResponseEntity<FacturaDetalle> respuesta= facturaDetalleController.save(facturaDetalle);
        assertEquals(200, respuesta.getBody()); }
    @Test
    public void  testUpdateExistente(){
        when(facturaDetalleService.update(1, facturaDetalle)).thenReturn(facturaDetalle);
        ResponseEntity<FacturaDetalle>respuesta= facturaDetalleController.update(1,facturaDetalle);
        assertEquals(200,respuesta.getStatusCodeValue());}
    @Test
    public void testUpdateNoExistente(){
        when(facturaDetalleService.update(eq(2),any(FacturaDetalle.class))).thenReturn(null);
        ResponseEntity<FacturaDetalle>respuesta = facturaDetalleController.update(2, facturaDetalle);
        assertEquals(404, respuesta.getStatusCodeValue()); }
    @Test
    public void testDelete(){
        doNothing().when(facturaDetalleService).delete(1);
        ResponseEntity<Void> respuesta = facturaDetalleController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(facturaDetalleService, times(1)).delete(1);}}