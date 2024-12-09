package Kelompok2_RPL.AplikasiKlinik.User.Admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin loginAdmin(String username, String password){
        try{
            Optional<Admin> admin = adminRepository.FindUsernameAdmin(username);
            if(passwordEncoder.matches(password, admin.get().getPassword())){
                return admin.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}