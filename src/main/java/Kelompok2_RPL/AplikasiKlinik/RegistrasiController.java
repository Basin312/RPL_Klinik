package Kelompok2_RPL.AplikasiKlinik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrasiController {
    
    @GetMapping("/register")
    public String halamanRegister(){
        return "index";
    }
}
