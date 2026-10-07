package com.nabila.pelanggan.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nabila.pelanggan.entity.Pelanggan;
import com.nabila.pelanggan.service.PelangganService;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {

    @Autowired
    private PelangganService pelangganService;

    @GetMapping
    public List<Pelanggan> getAllPelanggan() {
        return pelangganService.getAllPelanggan();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelanggan> getPelangganById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pelangganService.getPelangganById(id));
    }

    @PostMapping
    public ResponseEntity<Pelanggan> createPelanggan(@RequestBody Pelanggan pelanggan) {
        return ResponseEntity.ok(pelangganService.savePelanggan(pelanggan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelanggan> updatePelanggan(@PathVariable("id") Long id, @RequestBody Pelanggan pelanggan) {
        return ResponseEntity.ok(pelangganService.updatePelanggan(id, pelanggan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelanggan(@PathVariable("id") Long id) {
        pelangganService.deletePelanggan(id);
        return ResponseEntity.ok().build();
    }
}