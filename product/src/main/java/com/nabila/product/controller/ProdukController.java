package com.nabila.product.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nabila.product.entity.JenisProduk;
import com.nabila.product.entity.Produk;
import com.nabila.product.service.JenisProdukService;
import com.nabila.product.service.ProdukService;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {

    @Autowired
    private ProdukService produkService;

    @Autowired
    private JenisProdukService jenisProdukService;

    // --- ENDPOINT PRODUK ---
    @GetMapping
    public List<Produk> getAllProduk(@RequestParam(value = "idjenis", required = false) Long idjenis) {
        if (idjenis != null) {
            return produkService.getAllBarangByIdJenis(idjenis);
        }
        return produkService.getAllProduk();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produk> getProdukById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(produkService.getProdukById(id));
    }

    @PostMapping
    public ResponseEntity<Produk> createProduk(@RequestBody Produk produk) {
        return ResponseEntity.ok(produkService.saveProduk(produk));
    }

    // --- UPDATE PRODUK (PUT) ---
    @PutMapping("/{id}")
    public ResponseEntity<Produk> updateProduk(
            @PathVariable("id") Long id,
            @RequestBody Produk produk) {

        Produk updatedProduk = produkService.updateProduk(id, produk);

        if (updatedProduk != null) {
            return ResponseEntity.ok(updatedProduk);
        }

        return ResponseEntity.notFound().build();
    }

    // --- HAPUS PRODUK (DELETE) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduk(@PathVariable("id") Long id) {
        Produk produk = produkService.getProdukById(id);

        if (produk == null) {
            return ResponseEntity.notFound().build();
        }

        produkService.deleteProduk(id);
        return ResponseEntity.noContent().build();
    }


    // --- ENDPOINT JENIS PRODUK ---
    @GetMapping("/jenis")
    public List<JenisProduk> getAllJenisProduk() {
        return jenisProdukService.getAllJenisProduk();
    }

    @GetMapping("/jenis/{id}")
    public ResponseEntity<JenisProduk> getJenisProdukById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(jenisProdukService.getJenisProdukById(id));
    }

    @PostMapping("/jenis")
    public ResponseEntity<JenisProduk> createJenisProduk(@RequestBody JenisProduk jenisProduk) {
        return ResponseEntity.ok(jenisProdukService.saveJenisProduk(jenisProduk));
    }

}