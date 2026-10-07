package com.nabila.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nabila.order.vo.PelangganVO;
import com.nabila.order.vo.ProdukVO;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long produkId;
    private Long pelangganId;
    private LocalDate tglTrans;
    private Integer jumlah;
    private Double total;

    @JsonIgnore
    @Transient
    private PelangganVO pelanggan;

    @JsonIgnore
    @Transient
    private ProdukVO produk;

    public Order() {}

    public Order(Long id, Long produkId, Long pelangganId, LocalDate tglTrans, Integer jumlah, Double total) {
        this.id = id;
        this.produkId = produkId;
        this.pelangganId = pelangganId;
        this.tglTrans = tglTrans;
        this.jumlah = jumlah;
        this.total = total;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProdukId() { return produkId; }
    public void setProdukId(Long produkId) { this.produkId = produkId; }

    public Long getPelangganId() { return pelangganId; }
    public void setPelangganId(Long pelangganId) { this.pelangganId = pelangganId; }

    public LocalDate getTglTrans() { return tglTrans; }
    public void setTglTrans(LocalDate tglTrans) { this.tglTrans = tglTrans; }

    public Integer getJumlah() { return jumlah; }
    public void setJumlah(Integer jumlah) { this.jumlah = jumlah; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    // Getter dan Setter untuk Virtual Object
    public PelangganVO getPelanggan() { return pelanggan; }
    public void setPelanggan(PelangganVO pelanggan) { this.pelanggan = pelanggan; }

    public ProdukVO getProduk() { return produk; }
    public void setProduk(ProdukVO produk) { this.produk = produk; }
}