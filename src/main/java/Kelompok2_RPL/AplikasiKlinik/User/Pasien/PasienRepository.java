package Kelompok2_RPL.AplikasiKlinik.User.Pasien;

import java.util.Optional;

public interface PasienRepository {
    void save(Pasien user) throws Exception;
    Optional<Pasien> FindUsername(String username);
}
