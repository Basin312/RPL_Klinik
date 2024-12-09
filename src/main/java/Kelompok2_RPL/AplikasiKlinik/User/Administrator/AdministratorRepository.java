package Kelompok2_RPL.AplikasiKlinik.User.Administrator;

import java.util.Optional;

public interface AdministratorRepository {
    void save(Administrator user) throws Exception;
    Optional<Administrator> FindUsernameAdministrator(String username);
}
