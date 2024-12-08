package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.util.List;

import Kelompok2_RPL.AplikasiKlinik.pendaftaran.Pendaftaran;

public interface DokterRepository {
    List<Pendaftaran> getAllPendaftaran(int id_Dokter);
}
