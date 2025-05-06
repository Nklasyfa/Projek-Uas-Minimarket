package SupermarketPack;

// Produk class
public class Produk {
    private String nama;
    private double harga;
    
    public Produk(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }
    
    public String getNama() {
        return nama;
    }
    
    public double getHarga() {
        return harga;
    }
}