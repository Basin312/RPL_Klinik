package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.util.List;

import Kelompok2_RPL.AplikasiKlinik.pendaftaran.PendaftaranKonsultasi;

public interface DokterRepository {
    List<PendaftaranKonsultasi> getAllPendaftaran(int id_Dokter);
    List<PendaftaranKonsultasi> searchPendaftaranByName(int id_Dokter, String filter);
}
