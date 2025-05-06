package SupermarketPack;

import java.util.ArrayList;

// Abstract class untuk abstrack statistik
public abstract class AbstractProdukStatistik {
    public abstract void hitungStatistik(int[][] data, ArrayList<Kategori> kategoriList);
}