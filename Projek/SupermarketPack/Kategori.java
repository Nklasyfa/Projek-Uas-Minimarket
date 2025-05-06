package SupermarketPack;

import java.util.ArrayList;

// Kategori class
public class Kategori {
    private int id;
    private String nama;
    private ArrayList<Produk> listProduk;
    
    public Kategori(int id, String nama) {
        this.id = id;
        this.nama = nama;
        this.listProduk = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public String getNama() {
        return nama;
    }
    
    public ArrayList<Produk> getListProduk() {
        return listProduk;
    }
    
    public void addProduk(Produk produk) {
        this.listProduk.add(produk);
    }
}