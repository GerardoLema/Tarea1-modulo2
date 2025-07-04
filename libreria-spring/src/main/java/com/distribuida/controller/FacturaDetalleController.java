package com.distribuida.controller;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.service.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/facturaDetalles")
public class FacturaDetalleController {
    @Autowired
    private FacturaDetalleService facturaDetalleService;
    @GetMapping
    public ResponseEntity<List<FacturaDetalle>> fibdAll() {
        return ResponseEntity.ok(facturaDetalleService.findAll());}
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDetalle> findOne(@PathVariable int id) {
        FacturaDetalle facturaDetalle = facturaDetalleService.findOne(id);
        if (facturaDetalle == null) {
            return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(facturaDetalle); }
    @PostMapping
    public ResponseEntity<FacturaDetalle> save(@RequestBody FacturaDetalle facturaDetalle) {
        return ResponseEntity.ok(facturaDetalleService.save(facturaDetalle));}
    @PutMapping("/{id}")
    public ResponseEntity<FacturaDetalle> update(@PathVariable int id, @RequestBody FacturaDetalle facturaDetalle) {
        FacturaDetalle facturaDetalle1 = facturaDetalleService.update(id, facturaDetalle);
        if (facturaDetalle1 == null) {
            return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(facturaDetalle);}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        facturaDetalleService.delete(id);
        return ResponseEntity.noContent().build();}}