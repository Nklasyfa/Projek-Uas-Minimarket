package SupermarketPack;

// Subclass untuk item konsumsi(Food and Drinks)
public class ItemKonsumsi extends ItemBelanja {
    private String masaKadaluarsa;
    
    public ItemKonsumsi(String nama, double harga, String jenis, String alamat, String pengantar, int jumlah, double diskon, double subtotal, String masaKadaluarsa) {
        super(nama, harga, jenis, alamat, pengantar, jumlah, diskon, subtotal);
        this.masaKadaluarsa = masaKadaluarsa;
    }
    
    // Override untuk mneampilkan masa kadaluarsa
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Masa Kadaluarsa: " + masaKadaluarsa);
    }
}