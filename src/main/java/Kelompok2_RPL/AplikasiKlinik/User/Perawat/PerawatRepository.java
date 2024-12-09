package Kelompok2_RPL.AplikasiKlinik.User.Perawat;

import java.util.Optional;

public interface PerawatRepository {
    void save(Perawat user) throws Exception;
    Optional<Perawat> FindPerawatUsername(String username);
}
