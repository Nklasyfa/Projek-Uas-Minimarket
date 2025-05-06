package SupermarketPack;

// Subclass for fresh items (Vegetables and Fruits)
public class ItemSegar extends ItemBelanja {
    private String masaKesegaran;
    
    public ItemSegar(String nama, double harga, String jenis, String alamat, String pengantar, int jumlah, double diskon, double subtotal, String masaKesegaran) {
        super(nama, harga, jenis, alamat, pengantar, jumlah, diskon, subtotal);
        this.masaKesegaran = masaKesegaran;
    }
    
    // Override to add freshness information
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Masa Kesegaran: " + masaKesegaran);
    }
}