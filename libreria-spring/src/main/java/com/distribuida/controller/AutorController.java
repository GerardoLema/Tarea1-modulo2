package com.distribuida.controller;
import com.distribuida.model.Autor;
import com.distribuida.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/autores")
public class AutorController {
    @Autowired
    private AutorService autorService;
    @GetMapping
    public ResponseEntity<List<Autor>> fibdAll() {
        return ResponseEntity.ok(autorService.findAll());}
    @GetMapping("/{id}")
    public ResponseEntity<Autor> findOne(@PathVariable int id) {
        Autor autor = autorService.findOne(id);
        if (autor == null) {
            return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(autor); }
    @PostMapping
    public ResponseEntity<Autor> save(@RequestBody Autor autor) {
        return ResponseEntity.ok(autorService.save(autor));}
    @PutMapping("/{id}")
    public ResponseEntity<Autor> update(@PathVariable int id, @RequestBody Autor Autor) {
        Autor autor1 = autorService.update(id, Autor);
        if (autor1 == null) {
            return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(Autor);}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        autorService.delete(id);
        return ResponseEntity.noContent().build();}}
