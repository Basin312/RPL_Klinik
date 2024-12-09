package Kelompok2_RPL.AplikasiKlinik.User.Dokter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DokterService {
    @Autowired
    private DokterRepository dokterRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Dokter loginDokter(String username, String password){
        try{
            Optional<Dokter> dokter = dokterRepository.findByUsername(username);
            if(passwordEncoder.matches(password, dokter.get().getPassword())){
                return dokter.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
