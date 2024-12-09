package Kelompok2_RPL.AplikasiKlinik.User.Pasien;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasienService {
    @Autowired
    private PasienRepository pasienRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Pasien loginPasien(String username, String password){
        try{
            Optional<Pasien> pasien = pasienRepository.FindUsername(username);
            if(passwordEncoder.matches(password, pasien.get().getPassword())){
                return pasien.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
