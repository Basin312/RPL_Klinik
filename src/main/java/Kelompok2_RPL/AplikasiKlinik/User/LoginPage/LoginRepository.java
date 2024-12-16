package Kelompok2_RPL.AplikasiKlinik.User.LoginPage;

import java.util.Optional;

public interface LoginRepository {
    Optional<Login> findPasien(String email);
    Optional<Login> findAdmin(String email);
    Optional<Login> findDokter(String email);
    Optional<Login> findAdministrator(String email);
    Optional<Login> findPerawat(String email);
}
