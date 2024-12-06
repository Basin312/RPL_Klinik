package Kelompok2_RPL.AplikasiKlinik.register;

import java.util.Optional;

import Kelompok2_RPL.AplikasiKlinik.Pasien;

public interface RegisterRepository {
    void save(Pasien pasien) throws Exception;
    Optional<Pasien> findByEmail(String email);
}
