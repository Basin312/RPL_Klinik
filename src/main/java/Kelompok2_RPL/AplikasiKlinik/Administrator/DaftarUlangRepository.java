package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.List;

public interface DaftarUlangRepository {
    List<PasienStatusDTO> findAllWithStatus();
    List<PasienStatusDTO> findByName(String keyword);
    DetailPasienDTO findDetailPasienById(int id);
    void updateIsDaftarByIdPasien(int idPasien, boolean is_daftar);
    void updateDaftarUlangWithAntrian(int idPasien, int idJadwal);
}
