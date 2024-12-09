package Kelompok2_RPL.AplikasiKlinik.User.LoginPage;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Login loginPasien(String email, String password){
        try{
            Optional<Login> pasien = loginRepository.findPasien(email);
            if(pasien.get().getPassword().equals(password)){
                pasien.get().setRoles("pasien");
                return  pasien.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Login loginDokter(String email, String password){
        try{
            Optional<Login> dokter = loginRepository.findDokter(email);
            if(dokter.get().getPassword().equals(password)){
                dokter.get().setRoles("dokter");
                return  dokter.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Login loginPerawat(String email, String password){
        try{
            Optional<Login> perawat = loginRepository.findPerawat(email);
            if(perawat.get().getPassword().equals(password)){
                perawat.get().setRoles("perawat");
                return  perawat.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Login loginAdmin(String email, String password){
        try{
            Optional<Login> admin = loginRepository.findAdmin(email);
            if(admin.get().getPassword().equals(password)){
                admin.get().setRoles("admin");
                return  admin.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Login loginAdministrator(String email, String password){
        try{
            Optional<Login> administrator = loginRepository.findAdministrator(email);
            if(administrator.get().getPassword().equals(password)){
                administrator.get().setRoles("administrator");
                return  administrator.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
