package com.example.supermarket.model;

public class ItemBelanja {
    private String nama;
    private int jumlah;
    private double harga;
    
    public ItemBelanja(String nama, int jumlah, double harga) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
    }
    
    public String getNama() {
        return nama;
    }
    
    public int getJumlah() {
        return jumlah;
    }
    
    public double getHarga() {
        return harga;
    }
    
     public double hitungTotal() {
        return jumlah * harga;
    }
}
    