package Kelompok2_RPL.AplikasiKlinik.pendaftaran;

import java.util.List;

public interface PendaftaranKonsultasiRepository {
    List<PendaftaranKonsultasi> getAllPendaftaran(int id_Dokter);
    List<PendaftaranKonsultasi> searchPendaftaranByName(int id_Dokter, String filter);
}
