package com.example.supermarket.model;

public class ItemSegar extends Produk {
    private double berat;

    public ItemSegar(String nama, double harga, double berat) {
        super(nama, harga);
        this.berat = berat;
    }

    public double getBerat() {
        return berat;
    }

    public double hitungHargaTotal() {
        return getHarga() * berat;
    }
}
