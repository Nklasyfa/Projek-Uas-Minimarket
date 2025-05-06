package com.example.supermarket.model;

public abstract class AbstractProdukStatistik {
        protected String namaProduk;
        protected double harga;
        protected int jumlahTerjual;
    
        public AbstractProdukStatistik(String namaProduk, double harga, int jumlahTerjual) {
            this.namaProduk = namaProduk;
            this.harga = harga;
            this.jumlahTerjual = jumlahTerjual;
        }
    
        public abstract double hitungTotalPendapatan();
    
        public String getNamaProduk() {
            return namaProduk;
        }
    
        public double getHarga() {
            return harga;
        }
    
        public int getJumlahTerjual() {
            return jumlahTerjual;
        }
    }
        

