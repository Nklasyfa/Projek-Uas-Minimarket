package com.example.supermarket.controller;

import com.example.supermarket.model.ItemBelanja;
import com.example.supermarket.model.PembayaranInterface;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/supermarket")
public class SupermarketOnline {

    private final List<ItemBelanja> keranjang = new ArrayList<>();

    @PostMapping("/tambah")
    public String tambahItem(@RequestBody ItemBelanja item) {
        keranjang.add(item);
        return "Item ditambahkan: " + item.getNama();
    }

    @GetMapping("/total")
    public double hitungTotal() {
        return keranjang.stream().mapToDouble(ItemBelanja::hitungTotal).sum();
    }

    @PostMapping("/bayar")
    public String bayar(@RequestParam double total) {
        PembayaranInterface pembayaran = t -> System.out.println("Membayar total: " + t);
        pembayaran.prosesPembayaran(total);
        return "Pembayaran berhasil.";
    }
}
