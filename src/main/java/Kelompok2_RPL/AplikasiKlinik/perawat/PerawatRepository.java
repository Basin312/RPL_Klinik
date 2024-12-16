package Kelompok2_RPL.AplikasiKlinik.perawat;

import java.util.List;
import java.util.Optional;

import Kelompok2_RPL.AplikasiKlinik.pasien.Pasien;

public interface PerawatRepository {
    List<CheckUp> findAll();
    Optional<Pasien> findById(int id);
    void makeCheckup(double tinggi, double berat, String tekanan_darah,String tanggal,int id_Pasien);
    Optional<Pasien> findByName(String name);
    List<Pasien> findPasiens();
}
