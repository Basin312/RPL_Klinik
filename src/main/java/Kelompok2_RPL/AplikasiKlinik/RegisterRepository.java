package Kelompok2_RPL.AplikasiKlinik;

import java.util.Optional;

public interface RegisterRepository {
    void save(Pasien pasien) throws Exception;
    Optional<Pasien> findByUser(String username);
}
