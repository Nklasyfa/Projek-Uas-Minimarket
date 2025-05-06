package com.example.supermarket.service;

import com.example.supermarket.model.ItemBelanja;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupermarketService {

    private final List<ItemBelanja> keranjang = new ArrayList<>();

    public void tambahItem(ItemBelanja item) {
        keranjang.add(item);
    }

    public List<ItemBelanja> getKeranjang() {
        return keranjang;
    }

    public double hitungTotal() {
        return keranjang.stream().mapToDouble(ItemBelanja::hitungTotal).sum();
    }

    public void kosongkanKeranjang() {
        keranjang.clear();
    }
}
