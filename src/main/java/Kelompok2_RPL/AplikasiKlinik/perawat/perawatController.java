package Kelompok2_RPL.AplikasiKlinik.perawat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import Kelompok2_RPL.AplikasiKlinik.User.RequiredRole;

@Controller
public class perawatController {
    
    @GetMapping("/perawat")
    @RequiredRole("perawat")
    public String homePagePerawat(){
        return "/perawat/antriancheckup";
    }
}
