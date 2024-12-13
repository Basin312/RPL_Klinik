package Kelompok2_RPL.AplikasiKlinik.konsultasi;

import java.util.List;
import java.util.Optional;

public interface KonsultasiRepository {
    void tambahKonsultasi(String diagnosa, int id_Dokter, int id_Checkup);
    Optional<Konsultasi> getKonsultasiTerbaruByDokterAndCurrDate(int id_Dokter);
    List<Konsultasi> getKonsultasiByPasien(int id_Pasien);
}
