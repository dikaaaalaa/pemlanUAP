public class Mobil extends Kendaraan {
    private int jumlahKursi;

    public Mobil(String kode, String nama, double harga, int jumlahKursi) {
        super(kode, nama, harga);
        this.jumlahKursi = jumlahKursi;
    }

    public int getJumlahKursi() { return jumlahKursi; }
    public void setJumlahKursi(int jumlah) { this.jumlahKursi = jumlah; }
    
    @Override
    public void tampilInfo() {
        System.out.printf("[MOBIL] Kode: %-6s | Nama: %-20s | Kursi: %d | Tarif: Rp%,.0f/hari | Status: %s%n",
                getKodeKendaraan(), getNamaKendaraan(), jumlahKursi,
                getHargaSewaPerHari(), isTersedia() ? "Tersedia" : "Tidak Tersedia");
    }

    @Override
    public double hitungBiayaDasar(int lamaSewa) {
        double total = lamaSewa * getHargaSewaPerHari();
        if (jumlahKursi > 5) {
            total += 50000;
        }
        return total;
    }
}
