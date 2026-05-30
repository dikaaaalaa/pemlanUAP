import java.util.ArrayList;

public class GoDriveRentalSystem {
    private ArrayList<Kendaraan> daftarKendaraan;

    public GoDriveRentalSystem() {
        daftarKendaraan = new ArrayList<>();
        
        // Inisialisasi dengan beberapa kendaraan
        daftarKendaraan.add(new Mobil("MBL01", "Toyota Avanza", 350000, 7));
        daftarKendaraan.add(new Mobil("MBL02", "Daihatsu Sigra", 300000, 7));
        daftarKendaraan.add(new Mobil("MBL03", "Honda Brio", 280000, 5));
        daftarKendaraan.add(new Motor("MTR01", "Honda Vario", 80000, "Matik"));
        daftarKendaraan.add(new Motor("MTR02", "Yamaha NMAX", 100000, "Matik"));
        daftarKendaraan.add(new Motor("MTR03", "Kawasaki KLX", 90000, "Manual"));
    }

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
        System.out.println("[INFO] Kendaraan berhasil ditambahkan: " + k.getNamaKendaraan() + " (" + k.getKodeKendaraan() + ")");
    }

    public void tampilkanDaftarKendaraan() {
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Tidak ada kendaraan dalam daftar.");
            return;
        }
        System.out.println("\n=== DAFTAR ARMADA GODRIVE ===");
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarKendaraan.get(i).tampilInfo();
        }
    }

    public void sewaKendaraan(String kode, int lamaSewa, boolean isVIP) throws KendaraanTidakTersediaException {
        Kendaraan target = null;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                target = k;
                break;
            }
        }

        if (target == null || !target.isTersedia()) {
            throw new KendaraanTidakTersediaException(
                "Kendaraan dengan kode " + kode + " gagal disewa. Alasan: Kendaraan sedang disewa atau tidak ditemukan!"
            );
        }

        double biayaDasar = target.hitungBiayaDasar(lamaSewa);
        double diskonVIP = 0;
        double diskonLama = 0;

        // diskon vip 10%
        if (isVIP) {
            diskonVIP = biayaDasar * 0.10;
        }

        // diskon lama sewa > 7 hari 5%
        if (lamaSewa > 7) {
            diskonLama = biayaDasar * 0.05;
        }

        double totalBiaya = biayaDasar - diskonVIP - diskonLama;

        target.setTersedia(false);

        System.out.println("\n=== TRANSAKSI SEWA GODRIVE ===");
        System.out.println("Kendaraan Berhasil Disewa!");
        System.out.printf("Unit          : %s (%s)%n", target.getNamaKendaraan(), target.getKodeKendaraan());
        System.out.printf("Lama Sewa     : %d hari%n", lamaSewa);
        System.out.printf("Biaya Dasar Harian : Rp %,.0f%n", target.getHargaSewaPerHari() * lamaSewa);

        if (target instanceof Mobil) {
            Mobil m = (Mobil) target;
            if (m.getJumlahKursi() > 5) {
                System.out.printf("Tambahan Kursi (>5): Rp 50,000%n");
            }
        } else if (target instanceof Motor) {
            Motor mo = (Motor) target;
            if (mo.getJenisTransmisi().equalsIgnoreCase("Matik")) {
                System.out.printf("Asuransi Matik    : Rp %,.0f%n", 10000.0 * lamaSewa);
            }
        }

        if (isVIP) {
            System.out.printf("Diskon Member VIP (10%%): -Rp %,.0f%n", diskonVIP);
        }
        if (lamaSewa > 7) {
            System.out.printf("Diskon Sewa >7 hari (5%%): -Rp %,.0f%n", diskonLama);
        }

        System.out.println("----------------------------------------");
        System.out.printf("TOTAL BIAYA AKHIR: Rp %,.0f%n", totalBiaya);
    }

    public void kembalikanKendaraan(String kode) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                if (k.isTersedia()) {
                    System.out.println("[INFO] Kendaraan " + k.getNamaKendaraan() + " (" + kode + ") tidak sedang disewa.");
                } else {
                    k.setTersedia(true);
                    System.out.println("[INFO] Kendaraan " + k.getNamaKendaraan() + " (" + kode + ") berhasil dikembalikan. Status: Tersedia.");
                }
                return;
            }
        }
        System.out.println("[ERROR] Kendaraan dengan kode " + kode + " tidak ditemukan.");
    }
}
