package Kelompok2_RPL.AplikasiKlinik.catatan_obat;

import java.util.List;

public interface CatatanObatRepository {
    void tambahObat(String obat, String dosis, int id_Konsul);
    List<CatatanObat> getCatatanObatByPasien(int id_Pasien);
}
