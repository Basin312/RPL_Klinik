package Kelompok2_RPL.AplikasiKlinik.User.Admin;

import java.util.Optional;


public interface AdminRepository {
    void save(Admin user) throws Exception;
    Optional<Admin> FindUsernameAdmin(String username);
}
