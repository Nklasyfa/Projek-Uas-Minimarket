import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import SupermarketPack.Kategori;
import SupermarketPack.Produk;
import SupermarketPack.ItemBelanja;
import SupermarketPack.ItemKonsumsi;
import SupermarketPack.ItemSegar;
import SupermarketPack.PembayaranInterface; 
import SupermarketPack.AbstractProdukStatistik; 

public final class SupermarketOnline { 
    // Menambahkan final class untuk mencegah pewarisan lebih lanjut
    // Method main atau utama teteap sama
    public static void main(String[] args) throws Exception {
         // Object Instance dari class
        ArrayList<ItemBelanja> listSupermarketOnline = new ArrayList<>();
        ArrayList<Kategori> listKategori = new ArrayList<>();
        
       // Inisialisasi kategori
        listKategori.add(new Kategori(1, "Perlengkapan Rumah Tangga"));
        listKategori.add(new Kategori(2, "Skincare"));
        listKategori.add(new Kategori(3, "Makanan"));
        listKategori.add(new Kategori(4, "Minuman"));
        listKategori.add(new Kategori(5, "Sayuran"));
        listKategori.add(new Kategori(6, "Buah"));
        
       // Inisialisasi produk
        initializeProducts(listKategori);
        
      // Membuat objek Scanner untuk input pengguna
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        double totalBelanja = 0;
        ArrayList<ItemBelanja> keranjang = new ArrayList<>();
        
        // Contoh array multidimensi untuk menyimpan statistik penjualan (kategori, produk, jumlah terjual)
        int[][] statistikPenjualan = new int[6][4]; // 6 kategori, masing-masing dengan 4 produk
        
        // Main menu loop
        while (!choice.equals("0")) {
            System.out.println("\n+++++++++++++++++++ SELAMAT DATANG DI SUPERMARKET ONLINE ++++++++++++++++++++++++++++++++++++");
            System.out.println("\n++++++++++++++++++  NIKMATI BELANJA DENGAN MUDAH DAN CEPAT ++++++++++++++++++++++++++++++++++");
            System.out.println("\n|=========================     Silahkan Memilih Menu       ==================================|");
            System.out.println("|1.| Lihat Kategori Produk                                                                   |");
            System.out.println("|2.| Lihat Keranjang                                                                         |");
            System.out.println("|3.| Checkout                                                                                |");
            System.out.println("|4.| Lihat Statistik Penjualan                                                               |"); // Menu baru
            System.out.println("|0.| Keluar                                                                                  |");
            System.out.print("|Pilihan Anda:  ");
            choice = scanner.nextLine();

            // Process pilihan user
            if (choice.equals("1")) {
                showCategories(scanner, listKategori, keranjang, totalBelanja);
                totalBelanja = calculateTotal(keranjang);
            } else if (choice.equals("2")) {
                viewCart(keranjang, totalBelanja);
            } else if (choice.equals("3")) {
                if (keranjang.isEmpty()) {
                    System.out.println("Keranjang belanja Anda kosong. Silahkan tambahkan produk terlebih dahulu.");
                } else {
                    processPembayaran(scanner, keranjang, totalBelanja);
                    
                    // Update statistik penjualan menggunakan array multidimensi
                    for (ItemBelanja item : keranjang) {
                        for (int i = 0; i < listKategori.size(); i++) {
                            if (item.getJenis().equals(listKategori.get(i).getNama())) {
                                for (int j = 0; j < listKategori.get(i).getListProduk().size(); j++) {
                                    if (item.getNama().equals(listKategori.get(i).getListProduk().get(j).getNama())) {
                                        statistikPenjualan[i][j] += item.getJumlah();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    
                    keranjang.clear();
                    totalBelanja = 0;
                }
            } else if (choice.equals("4")) {
                // Menampilkan statistik penjualan menggunakan array multidimensi
                showSalesStatistics(statistikPenjualan, listKategori);
            } else if (choice.equals("0")) {
                System.out.println("Terima kasih telah berbelanja di Supermarket Online!");
            } else {
                System.out.println("Pilihan tidak valid. Silahkan coba lagi.");
            }
        }
        
        scanner.close();
    }
    
    // Menambahkan final method yang tidak bisa di-override oleh subclass
    protected static final void showSalesStatistics(int[][] statistics, ArrayList<Kategori> categories) {
        System.out.println("\n==================== STATISTIK PENJUALAN ====================");
        System.out.println("Kategori dan Produk yang Terjual:");
        
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("\nKategori: " + categories.get(i).getNama());
            ArrayList<Produk> products = categories.get(i).getListProduk();
            
            for (int j = 0; j < products.size(); j++) {
                System.out.println("  - " + products.get(j).getNama() + ": " + statistics[i][j] + " unit");
            }
        }
        
        // Implementasi abstract class untuk menghitung dan menampilkan statistik
        AbstractProdukStatistik statistik = new AbstractProdukStatistik() {
            @Override
            public void hitungStatistik(int[][] data, ArrayList<Kategori> kategoriList) {
                int totalProdukTerjual = 0;
                String kategoriTerlaris = "";
                String produkTerlaris = "";
                int maxKategori = 0;
                int maxProduk = 0;
                
                for (int i = 0; i < data.length; i++) {
                    int totalKategori = 0;
                    for (int j = 0; j < data[i].length; j++) {
                        totalProdukTerjual += data[i][j];
                        totalKategori += data[i][j];
                        
                        if (data[i][j] > maxProduk) {
                            maxProduk = data[i][j];
                            produkTerlaris = kategoriList.get(i).getListProduk().get(j).getNama();
                        }
                    }
                    
                    if (totalKategori > maxKategori) {
                        maxKategori = totalKategori;
                        kategoriTerlaris = kategoriList.get(i).getNama();
                    }
                }
                
                System.out.println("\nTotal Produk Terjual: " + totalProdukTerjual + " unit");
                System.out.println("Kategori Terlaris: " + (maxKategori > 0 ? kategoriTerlaris : "Belum ada penjualan"));
                System.out.println("Produk Terlaris: " + (maxProduk > 0 ? produkTerlaris : "Belum ada penjualan"));
            }
        };
        
        statistik.hitungStatistik(statistics, categories);
    }
    
    // Metode untuk menampilkan kategori
    protected static void showCategories(Scanner scanner, ArrayList<Kategori> listKategori, ArrayList<ItemBelanja> keranjang, double totalBelanja) {
        String categoryChoice = "";
        
        while (!categoryChoice.equals("0")) {
            System.out.println("\n==================== KATEGORI PRODUK ====================");
            for (int i = 0; i < listKategori.size(); i++) {
                System.out.println((i + 1) + ". " + listKategori.get(i).getNama());
            }
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih kategori: ");
            categoryChoice = scanner.nextLine();
            
            if (categoryChoice.equals("0")) {
                return;
            }
            
            try {
                int kategoriChoice = Integer.parseInt(categoryChoice);
                if (kategoriChoice >= 1 && kategoriChoice <= listKategori.size()) {
                    Kategori selectedKategori = listKategori.get(kategoriChoice - 1);
                    showProducts(scanner, selectedKategori, keranjang);
                } else {
                    System.out.println("Pilihan kategori tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Silahkan masukkan angka.");
            }
        }
    }
    
      // Metode untuk menampilkan produk dalam sebuah kategori
    protected static void showProducts(Scanner scanner, Kategori kategori, ArrayList<ItemBelanja> keranjang) {
        String productChoice = "";
        
        while (!productChoice.equals("0")) {
            System.out.println("\n==================== " + kategori.getNama() + " ====================");
            
            ArrayList<Produk> produkList = kategori.getListProduk();
            
            // Inner class untuk pengurutan produk (implementasi inner class)
            class ProductSorter {
                public void sortByPriceHighToLow(ArrayList<Produk> products) {
                    Collections.sort(products, new Comparator<Produk>() {
                        public int compare(Produk p1, Produk p2) {
                            return Double.compare(p2.getHarga(), p1.getHarga());
                        }
                    });
                    System.out.println("Produk diurutkan dari harga tertinggi ke terendah.");
                }
            }
            
                       // Membuat instance dari inner class dan menggunakannya
            ProductSorter sorter = new ProductSorter();
            sorter.sortByPriceHighToLow(produkList);
            
            for (int i = 0; i < produkList.size(); i++) {
                System.out.println((i + 1) + ". " + produkList.get(i).getNama() + " - Rp" + produkList.get(i).getHarga());
            }
            
            System.out.println("0. Kembali ke Menu Kategori");
            System.out.print("Pilih produk (1-" + produkList.size() + "): ");
            productChoice = scanner.nextLine();
            
            if (productChoice.equals("0")) {
                return;
            }
            
            try {
                int produkIndex = Integer.parseInt(productChoice) - 1;
                if (produkIndex >= 0 && produkIndex < produkList.size()) {
                    Produk selectedProduk = produkList.get(produkIndex);
                    
                    System.out.print("Jumlah yang ingin dibeli: ");
                    int jumlah = Integer.parseInt(scanner.nextLine());
                    
                    if (jumlah > 0) {
                        double subtotal = selectedProduk.getHarga() * jumlah;
                        
                         // Membuat item berdasarkan kategori untuk demonstrasi method oveeriding
                        ItemBelanja item;
            
                        
                        // Membuat jenis barang tertentu berdasarkan kategori untuk demonstrasi overriding
                        if (kategori.getNama().equals("Makanan") || kategori.getNama().equals("Minuman")) {
                            item = new ItemKonsumsi(selectedProduk.getNama(), selectedProduk.getHarga(),kategori.getNama(), "", "", jumlah, 0, subtotal, "7 hari" ); // Expiry period for food/drinks
                        } else if (kategori.getNama().equals("Sayuran") || kategori.getNama().equals("Buah")) {
                            item = new ItemSegar( selectedProduk.getNama(),selectedProduk.getHarga(), kategori.getNama(), "","",jumlah,0, subtotal, "3 hari" );// Shorter expiry for fresh items 
                        } else {
                            item = new ItemBelanja( selectedProduk.getNama(), selectedProduk.getHarga(), kategori.getNama(), "", "", jumlah, 0, subtotal );
                        }
                        
                        keranjang.add(item);
                        
                        System.out.println("\nProduk berhasil ditambahkan ke keranjang!");
                        item.tampilkanInfo();
                    } else {
                        System.out.println("Jumlah harus lebih dari 0.");
                    }
                } else {
                    System.out.println("Pilihan produk tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Silahkan masukkan angka.");
            }
        }
    }
    
    // Inner class untuk pembuatan struk (contoh lain dari inner class)
    public static class ReceiptGenerator {
        private ArrayList<ItemBelanja> items;
        private double totalAmount;
        private double discount;
        private String shippingAddress;
        private String courier;
        private String paymentMethod;
        private String accountNumber;
        
        public ReceiptGenerator(ArrayList<ItemBelanja> items, double totalAmount, double discount, String shippingAddress, String courier, String paymentMethod, String accountNumber) {
            this.items = items;
            this.totalAmount = totalAmount;
            this.discount = discount;
            this.shippingAddress = shippingAddress;
            this.courier = courier;
            this.paymentMethod = paymentMethod;
            this.accountNumber = accountNumber;
        }
        
        public void generateReceipt() {
            System.out.println("\n================================================================");
            System.out.println("                          STRUK BELANJA                               ");
            System.out.println("================================================================");
            
            System.out.println("----------------------------------------------------------------");
            System.out.printf("%-5s %-25s %-10s %-10s %-10s\n", "No", "Nama Produk", "Harga", "Jumlah", "Subtotal");
            System.out.println("----------------------------------------------------------------");
            
            double subtotalAll = 0;
            for (int i = 0; i < items.size(); i++) {
                ItemBelanja item = items.get(i);
                subtotalAll += item.getSubtotal();
                System.out.printf("%-5d %-25s Rp%-10.0f %-10d Rp%-10.0f\n", (i + 1), item.getNama(), item.getHarga(), item.getJumlah(), item.getSubtotal());
            }
            
            System.out.println("----------------------------------------------------------------");
            System.out.printf("%-41s Rp%-10.0f\n", "Subtotal Belanja:", subtotalAll);
            
            if (discount > 0) {
                System.out.printf("%-41s -Rp%-10.0f\n", "Diskon (MARKET76):", discount);
            }
            
            System.out.printf("%-41s Rp%-10.0f\n", "Total Pembayaran:", totalAmount);
            System.out.println("----------------------------------------------------------------");
            
            // Shipping and payment information
            System.out.println("INFORMASI PENGIRIMAN & PEMBAYARAN:");
            System.out.println("Alamat Pengiriman: " + shippingAddress);
            System.out.println("Pengantar: " + courier);
            System.out.println("Metode Pembayaran: " + paymentMethod);
            
            if (!paymentMethod.equals("COD") && !accountNumber.isEmpty()) {
                System.out.println("Rekening Transfer: " + accountNumber + " a.n. Supermarket Online");
            }
            
            System.out.println("|===============================================================|");
            System.out.println("|    Terima kasih telah berbelanja di Supermarket Online!       |");
            System.out.println("|       Simpan struk ini sebagai bukti pembelian Anda.          |");
            System.out.println("|===============================================================|");
        }
    }
    
    // Method untuk menghitung total belanja
    protected static double calculateTotal(ArrayList<ItemBelanja> keranjang) {
        double total = 0;
        for (ItemBelanja item : keranjang) {
            total += item.getSubtotal();
        }
        return total;
    }
    
        // Method untuk melihat keranjang belanja
    protected static void viewCart(ArrayList<ItemBelanja> keranjang, double totalBelanja) {
        if (keranjang.isEmpty()) {
            System.out.println("\nKeranjang belanja Anda kosong.");
            return;
        }
        
        System.out.println("\n==================== KERANJANG BELANJA ====================");
        for (int i = 0; i < keranjang.size(); i++) {
            System.out.println("Item #" + (i + 1));
            keranjang.get(i).tampilkanInfo();
            System.out.println("------------------------------------------------------------");
        }
        
        System.out.println("Total Belanja: Rp" + totalBelanja);
    }
    
        // Method untuk proses pembayaran
    protected static void processPembayaran(Scanner scanner, ArrayList<ItemBelanja> keranjang, double totalBelanja) {
        System.out.println("\n==================== PROSES PEMBAYARAN ====================");
        System.out.println("Total Belanja: Rp" + totalBelanja);
        
        // menerapkan code diskon
        System.out.print("Masukkan kode diskon (kosongkan jika tidak ada): ");
        String diskonCode = scanner.nextLine();
        
        double finalTotal = totalBelanja;
        double diskon = 0;
        if (diskonCode.equals("MARKET76")) {
            diskon = totalBelanja * 0.1; // 10% discount
            finalTotal = totalBelanja - diskon;
            System.out.println("Diskon 10% berhasil diterapkan: -Rp" + diskon);
            System.out.println("Total setelah diskon: Rp" + finalTotal);
        } else if (!diskonCode.isEmpty()) {
            System.out.println("Kode diskon tidak valid.");
        }
        
        // Menginput alamat user
        System.out.print("\nMasukkan alamat pengiriman: ");
        String alamat = scanner.nextLine();
        
        // Input nama kurir
        System.out.print("Masukkan nama pengantar paket: ");
        String pengantar = scanner.nextLine();
        
        // Perbarui alamat dan kurir untuk semua item di keranjang
        for (ItemBelanja item : keranjang) {
            item.setAlamat(alamat);
            item.setPengantar(pengantar);
        }
        
        // Pilih metode pembayaran
        System.out.println("\nPilih metode pembayaran:");
        System.out.println("1. COD (Cash On Delivery)");
        System.out.println("2. Transfer Bank");
        System.out.print("Pilihan Anda: ");
        String paymentChoice = scanner.nextLine();
        
        String selectedBank = "";
        String rekening = "";
        
        // Menggunakan interface untuk proses pembayaran
        PembayaranInterface metodePembayaran;
        
        if (paymentChoice.equals("1")) {
            System.out.println("\nAnda memilih pembayaran COD (Cash On Delivery)");
            System.out.println("Silahkan siapkan uang tunai sebesar Rp" + finalTotal + " saat barang datang.");
             
            // Implementasi interface dengan anonymous class untuk COD
            metodePembayaran = new PembayaranInterface() {
                @Override
                public void prosesPembayaran(double jumlah) {
                    System.out.println("Pembayaran COD sebesar Rp" + jumlah + " akan dilakukan saat barang tiba");
                }
                
                @Override
                public String getDetailPembayaran() {
                    return "COD - Bayar saat barang diterima";
                }
            };
            
            metodePembayaran.prosesPembayaran(finalTotal);
            
        } else if (paymentChoice.equals("2")) {
            System.out.println("\nAnda memilih pembayaran Transfer Bank");
            System.out.println("Pilih bank tujuan transfer:");
            System.out.println("1. Bank BCA");
            System.out.println("2. Bank Mandiri");
            System.out.println("3. Bank BNI");
            System.out.print("Pilihan Bank: ");
            String bankChoice = scanner.nextLine();
            
            if (bankChoice.equals("1")) {
                selectedBank = "Bank BCA";
                rekening = "1234567890";
            } else if (bankChoice.equals("2")) {
                selectedBank = "Bank Mandiri";
                rekening = "0987654321";
            } else if (bankChoice.equals("3")) {
                selectedBank = "Bank BNI";
                rekening = "1122334455";
            } else {
                System.out.println("Pilihan bank tidak valid. Menggunakan Bank BCA sebagai default.");
                selectedBank = "Bank BCA";
                rekening = "1234567890";
            }
            
            // Implementasi interface dengan anonymous class untuk Transfer Bank
            final String finalSelectedBank = selectedBank;
            final String finalRekening = rekening;
            metodePembayaran = new PembayaranInterface() {
                @Override
                public void prosesPembayaran(double jumlah) {
                    System.out.println("\nSilahkan transfer ke rekening " + finalSelectedBank + ": " + finalRekening + " Supermarket Online");
                    System.out.println("Total yang harus dibayar: Rp" + jumlah);
                    System.out.println("Harap transfer dalam waktu 24 jam dan simpan bukti transfer.");
                }
                
                @Override
                public String getDetailPembayaran() {
                    return finalSelectedBank + " - " + finalRekening;
                }
            };
            
            metodePembayaran.prosesPembayaran(finalTotal);
            
        } else {
            System.out.println("\nPilihan metode pembayaran tidak valid. Menggunakan COD sebagai default.");
            System.out.println("Silahkan siapkan uang tunai sebesar Rp" + finalTotal + " saat barang datang.");
            
            // Default ke COD jika pilihan tidak valid
            metodePembayaran = new PembayaranInterface() {
                @Override
                public void prosesPembayaran(double jumlah) {
                    System.out.println("Pembayaran COD sebesar Rp" + jumlah + " akan dilakukan saat barang tiba");
                }
                
                @Override
                public String getDetailPembayaran() {
                    return "COD - Bayar saat barang diterima";
                }
            };
            
            metodePembayaran.prosesPembayaran(finalTotal);
        }
        
        //Menggunakan inner class untuk membuat struck
        ReceiptGenerator receiptGen = new ReceiptGenerator(keranjang, finalTotal, diskon, alamat, pengantar,
                paymentChoice.equals("2") ? selectedBank : "COD", rekening);
        receiptGen.generateReceipt();
    }
    
    //methode untjk mencetak struk(versi asli - disimpansebagai referensi tetapi tidak dipanggil secara langsung)
    protected static void printReceipt(ArrayList<ItemBelanja> keranjang, double finalTotal, double diskon, String alamat, String pengantar, String metodePembayaran, String rekening) {
        System.out.println("\n================================================================");
        System.out.println("                          STRUK BELANJA                               ");
        System.out.println("================================================================");
        
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-5s %-25s %-10s %-10s %-10s\n", "No", "Nama Produk", "Harga", "Jumlah", "Subtotal");
        System.out.println("----------------------------------------------------------------");
        
        double subtotalAll = 0;
        for (int i = 0; i < keranjang.size(); i++) {
            ItemBelanja item = keranjang.get(i);
            subtotalAll += item.getSubtotal();
            System.out.printf("%-5d %-25s Rp%-10.0f %-10d Rp%-10.0f\n", (i + 1), item.getNama(), item.getHarga(), item.getJumlah(), item.getSubtotal());
        }
        
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-41s Rp%-10.0f\n", "Subtotal Belanja:", subtotalAll);
        
        if (diskon > 0) {
            System.out.printf("%-41s -Rp%-10.0f\n", "Diskon (MARKET76):", diskon);
        }
        
        System.out.printf("%-41s Rp%-10.0f\n", "Total Pembayaran:", finalTotal);
        System.out.println("----------------------------------------------------------------");
        
        // Shipping and informasi pembayaran 
        System.out.println("INFORMASI PENGIRIMAN & PEMBAYARAN:");
        System.out.println("Alamat Pengiriman: " + alamat);
        System.out.println("Pengantar: " + pengantar);
        System.out.println("Metode Pembayaran: " + metodePembayaran);
        
        if (!metodePembayaran.equals("COD") && !rekening.isEmpty()) {
            System.out.println("Rekening Transfer: " + rekening + " a.n. Supermarket Online");
        }
        
        System.out.println("|===============================================================|");
        System.out.println("|    Terima kasih telah berbelanja di Supermarket Online!       |");
        System.out.println("|       Simpan struk ini sebagai bukti pembelian Anda.          |");
        System.out.println("|===============================================================|");
    }
    
    // Method untuk menganalisasi produk di setiap kategori 
    protected static void initializeProducts(ArrayList<Kategori> listKategori) {
        // kategori 1 peralatan rumah tangga
        Kategori kategori1 = listKategori.get(0);
        kategori1.addProduk(new Produk("Panci Pressure Cooker", 350000));
        kategori1.addProduk(new Produk("Setrika Listrik", 250000));
        kategori1.addProduk(new Produk("Blender", 300000));
        kategori1.addProduk(new Produk("Vacuum Cleaner", 800000));
        
        // kategori 2 skincare
        Kategori kategori2 = listKategori.get(1);
        kategori2.addProduk(new Produk("Face Wash", 75000));
        kategori2.addProduk(new Produk("Serum Vitamin C", 150000));
        kategori2.addProduk(new Produk("Moisturizer", 120000));
        kategori2.addProduk(new Produk("Sunscreen", 90000));
        
        // kategori 3 makanan
        Kategori kategori3 = listKategori.get(2);
        kategori3.addProduk(new Produk("Mie Instan (1 dus)", 95000));
        kategori3.addProduk(new Produk("Biskuit Assorted", 25000));
        kategori3.addProduk(new Produk("Coklat Bar", 15000));
        kategori3.addProduk(new Produk("Sereal", 40000));
        
        // kategori 4 minuman
        Kategori kategori4 = listKategori.get(3);
        kategori4.addProduk(new Produk("Air Mineral (1 dus)", 45000));
        kategori4.addProduk(new Produk("Teh Kemasan", 20000));
        kategori4.addProduk(new Produk("Kopi Instant", 35000));
        kategori4.addProduk(new Produk("Jus Kemasan", 18000));
        
        // kategori 5 sayuran
        Kategori kategori5 = listKategori.get(4);
        kategori5.addProduk(new Produk("Bayam (250g)", 10000));
        kategori5.addProduk(new Produk("Wortel (500g)", 15000));
        kategori5.addProduk(new Produk("Brokoli (250g)", 20000));
        kategori5.addProduk(new Produk("Kembang Kol (250g)", 18000));
        
        // kategori 6 buah
        Kategori kategori6 = listKategori.get(5);
        kategori6.addProduk(new Produk("Apel (1kg)", 35000));
        kategori6.addProduk(new Produk("Pisang (1 sisir)", 25000));
        kategori6.addProduk(new Produk("Jeruk (1kg)", 30000));
        kategori6.addProduk(new Produk("Anggur (500g)", 45000));
    }
}
