package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.List;

public interface DaftarUlangRepository {
    List<PasienStatusDTO> findAllWithStatus();
    DetailPasienDTO findDetailPasienById(int id);
}
