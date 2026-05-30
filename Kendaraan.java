public abstract class Kendaraan {
    private String kodeKendaraan;
    private String namaKendaraan;
    private double hargaSewaPerHari;
    private boolean isTersedia;

    public Kendaraan(String kode, String nama, double hargaSewa) {
        this.kodeKendaraan = kode;
        this.namaKendaraan = nama;
        this.hargaSewaPerHari = hargaSewa;
        this.isTersedia = true;
    }

    public String getKodeKendaraan() { return kodeKendaraan; }
    public void setKodeKendaraan(String kode) { this.kodeKendaraan = kode; }

    public String getNamaKendaraan() { return namaKendaraan; }
    public void setNamaKendaraan(String nama) { this.namaKendaraan = nama; }

    public double getHargaSewaPerHari() { return hargaSewaPerHari; }
    public void setHargaSewaPerHari(double harga) { this.hargaSewaPerHari = harga; }

    public boolean isTersedia() { return isTersedia; }
    public void setTersedia(boolean status) { this.isTersedia = status; }

    public abstract void tampilInfo();
    public abstract double hitungBiayaDasar(int lamaSewa);
}
