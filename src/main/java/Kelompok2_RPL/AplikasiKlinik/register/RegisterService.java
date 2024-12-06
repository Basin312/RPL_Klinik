package Kelompok2_RPL.AplikasiKlinik.register;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Kelompok2_RPL.AplikasiKlinik.Pasien;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean register(Pasien register ){
        try{

            Optional<Pasien> existingPasien =registerRepository.findByEmail(register.getEmail());
            if(existingPasien.isPresent()){
                System.out.println("Email already exists: " + register.getEmail());
                return false;
            }

            register.setPassword(passwordEncoder.encode(register.getPassword()));
            System.out.println("password encrption");
            registerRepository.save(register);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
