import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static GoDriveRentalSystem sistem = new GoDriveRentalSystem();

    public static void main(String[] args) {
        int pilihan;
        do {
            tampilMenu();
            System.out.print("Pilih menu: ");
            pilihan = bacaInt();
            System.out.println();
            switch (pilihan) {
                case 1: menuTambahKendaraan(); break;
                case 2: sistem.tampilkanDaftarKendaraan(); break;
                case 3: menuSewa(); break;
                case 4: menuKembalikan(); break;
                case 5: System.out.println("Terima kasih telah menggunakan GoDrive!"); break;
                default: System.out.println("[ERROR] Pilihan tidak valid. Silakan coba lagi.");
            }
            System.out.println();
        } while (pilihan != 5);
    }

    static void tampilMenu() {
        System.out.println("====== MENU GO DRIVE RENTAL SYSTEM ======");
        System.out.println("1. Tambah Kendaraan");
        System.out.println("2. Tampilkan Daftar Armada");
        System.out.println("3. Sewa Kendaraan");
        System.out.println("4. Kembalikan Kendaraan");
        System.out.println("5. Keluar");
    }

    static void menuTambahKendaraan() {
        System.out.print("Masukkan jenis kendaraan (mobil/motor): ");
        String jenis = scanner.nextLine().trim().toLowerCase();

        System.out.print("Masukkan kode kendaraan: ");
        String kode = scanner.nextLine().trim().toUpperCase();

        System.out.print("Masukkan nama kendaraan: ");
        String nama = scanner.nextLine().trim();

        System.out.print("Masukkan harga sewa per hari: ");
        double harga = bacaDouble();

        if (jenis.equals("mobil")) {
            System.out.print("Masukkan kapasitas kursi: ");
            int kursi = bacaInt();
            sistem.tambahKendaraan(new Mobil(kode, nama, harga, kursi));
        } else if (jenis.equals("motor")) {
            System.out.print("Masukkan jenis transmisi (Matik/Manual): ");
            String transmisi = scanner.nextLine().trim();
            sistem.tambahKendaraan(new Motor(kode, nama, harga, transmisi));
        } else {
            System.out.println("[ERROR] Jenis kendaraan tidak dikenali. Masukkan 'mobil' atau 'motor'.");
        }
    }

    static void menuSewa() {
        System.out.print("Masukkan kode kendaraan yang ingin disewa: ");
        String kode = scanner.nextLine().trim().toUpperCase();

        System.out.print("Masukkan durasi sewa (dalam hari): ");
        int lama = bacaInt();

        System.out.print("Apakah Anda Member VIP? (y/n): ");
        String vipInput = scanner.nextLine().trim().toLowerCase();
        boolean isVIP = vipInput.equals("y");

        try {
            sistem.sewaKendaraan(kode, lama, isVIP);
        } catch (KendaraanTidakTersediaException e) {
            System.out.println("Exception in thread \"main\" KendaraanTidakTersediaException: " + e.getMessage());
        }
    }

    static void menuKembalikan() {
        System.out.print("Masukkan kode kendaraan yang ingin dikembalikan: ");
        String kode = scanner.nextLine().trim().toUpperCase();
        sistem.kembalikanKendaraan(kode);
    }

    static int bacaInt() {
        try {
            int val = Integer.parseInt(scanner.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Input harus berupa angka. Menggunakan nilai 0.");
            return 0;
        }
    }

    static double bacaDouble() {
        try {
            double val = Double.parseDouble(scanner.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Input harus berupa angka. Menggunakan nilai 0.");
            return 0;
        }
    }
}
