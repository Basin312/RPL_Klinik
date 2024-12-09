package Kelompok2_RPL.AplikasiKlinik.User.Perawat;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PerawatService {
    @Autowired
    private PerawatRepository perawatRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Perawat loginPerawat(String username, String password){
        try{
            Optional<Perawat> perawat = perawatRepository.FindPerawatUsername(username);
            if(passwordEncoder.matches(password, perawat.get().getPassword())){
                return perawat.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
