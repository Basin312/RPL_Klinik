package Kelompok2_RPL.AplikasiKlinik.User.Administrator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Administrator loginAdministrator(String username, String password){
        try{
            Optional<Administrator> administrator = administratorRepository.FindUsernameAdministrator(username);
            if(passwordEncoder.matches(password, administrator.get().getPassword())){
                return administrator.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
