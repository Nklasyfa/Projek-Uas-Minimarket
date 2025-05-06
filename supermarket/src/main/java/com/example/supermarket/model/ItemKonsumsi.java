package com.example.supermarket.model;

public class ItemKonsumsi extends Produk {
    private String tanggalKadaluarsa;

    public ItemKonsumsi(String nama, double harga, String tanggalKadaluarsa) {
        super(nama, harga);
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public String getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }
}
