package com.nabila.order.vo;

import com.nabila.order.entity.Order;

public class ResponseTemplateVO {
    private Order order;
    private PelangganVO pelanggan;
    private ProdukVO produk;

    // Getters and Setters
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public PelangganVO getPelanggan() { return pelanggan; }
    public void setPelanggan(PelangganVO pelanggan) { this.pelanggan = pelanggan; }

    public ProdukVO getProduk() { return produk; }
    public void setProduk(ProdukVO produk) { this.produk = produk; }
}