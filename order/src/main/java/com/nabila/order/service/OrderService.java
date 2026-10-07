package com.nabila.order.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.nabila.order.entity.Order;
import com.nabila.order.repository.OrderRepository;
import com.nabila.order.vo.PelangganVO;
import com.nabila.order.vo.ProdukVO;
import com.nabila.order.vo.ResponseTemplateVO;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestClient restClient;

    // Mengambil Data Pelanggan dari Service Pelanggan (Port 8084)
    public PelangganVO getPelanggan(Long id) {
        return restClient.get()
                .uri("http://localhost:8084/api/pelanggan/" + id)
                .retrieve()
                .body(PelangganVO.class);
    }

    // Mengambil Data Produk dari Service Produk (Port 8083)
    public ProdukVO getProduk(Long id) {
        return restClient.get()
                .uri("http://localhost:8083/api/produk/" + id)
                .retrieve()
                .body(ProdukVO.class);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order getOrderWithDetail(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            PelangganVO pelanggan = getPelanggan(order.getPelangganId());
            ProdukVO produk = getProduk(order.getProdukId());
            
            order.setPelanggan(pelanggan);
            order.setProduk(produk);
        }
        return order;
    }

    public Order saveOrder(Order order) {
        // 1. Cek pelanggan
        PelangganVO pelanggan = getPelanggan(order.getPelangganId());
        if (pelanggan == null) {
            throw new RuntimeException("Pelanggan tidak ditemukan");
        }

        // 2. Cek produk
        ProdukVO produk = getProduk(order.getProdukId());
        if (produk == null) {
            throw new RuntimeException("Produk tidak ditemukan");
        }

        // 3. Ambil harga produk
        double hargaProduk = produk.getHarga();

        // 4. Hitung total
        double total = hargaProduk * order.getJumlah();
        order.setTotal(total);

        // 5. Simpan order ke database
        Order savedOrder = orderRepository.save(order);

        // 6. Sisipkan VO ke dalam object yang disave agar muncul di response Postman
        savedOrder.setPelanggan(pelanggan);
        savedOrder.setProduk(produk);

        return savedOrder;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Long id, Order order) {
        Order existing = orderRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setProdukId(order.getProdukId());
            existing.setPelangganId(order.getPelangganId());
            existing.setTglTrans(order.getTglTrans());
            existing.setJumlah(order.getJumlah());
            existing.setTotal(order.getTotal());
            return orderRepository.save(existing);
        }
        return null;
    }

    public List<ResponseTemplateVO> getOrderLengkap() {
        List<ResponseTemplateVO> voList = new ArrayList<>();
        List<Order> orderList = orderRepository.findAll();

        for (Order order : orderList) {
            ResponseTemplateVO vo = new ResponseTemplateVO();
            vo.setOrder(order);
            vo.setPelanggan(getPelanggan(order.getPelangganId()));
            vo.setProduk(getProduk(order.getProdukId()));
            voList.add(vo);
        }
        return voList;
    }
}