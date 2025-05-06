
package SupermarketPack;

// Kelas dasar untuk item belanja
public class ItemBelanja {
    private String nama;
    private double harga;
    private String jenis;
    private String alamat;
    private String pengantar;
    private int jumlah;
    private double diskon;
    private double subtotal;
    
    public ItemBelanja(String nama, double harga, String jenis, String alamat, String pengantar, int jumlah, double diskon, double subtotal) {
        this.nama = nama;
        this.harga = harga;
        this.jenis = jenis;
        this.alamat = alamat;
        this.pengantar = pengantar;
        this.jumlah = jumlah;
        this.diskon = diskon;
        this.subtotal = subtotal;
    }
    
    // Getters and setters
    public String getNama() {
        return nama;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public String getJenis() {
        return jenis;
    }
    
    public String getAlamat() {
        return alamat;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public String getPengantar() {
        return pengantar;
    }
    
    public void setPengantar(String pengantar) {
        this.pengantar = pengantar;
    }
    
    public int getJumlah() {
        return jumlah;
    }
    
    public double getDiskon() {
        return diskon;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
 // Method untuk menampilkan informasi barang - bisa di-override oleh subclass
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Jenis: " + jenis);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Jumlah: " + jumlah);
        System.out.println("Subtotal: Rp" + subtotal);
    }
}