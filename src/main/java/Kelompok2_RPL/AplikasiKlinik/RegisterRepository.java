package Kelompok2_RPL.AplikasiKlinik;

import java.util.Optional;

public interface RegisterRepository {
    void save(Register pasien) throws Exception;
    Optional<Register> findByEmail(String email);
}
