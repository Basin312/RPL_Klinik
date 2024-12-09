package Kelompok2_RPL.AplikasiKlinik.User.Dokter;

import java.util.Optional;

public interface DokterRepository {
    void save(Dokter user) throws Exception;
    Optional<Dokter> findByUsername(String username);
}
