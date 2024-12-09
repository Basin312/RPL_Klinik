package Kelompok2_RPL.AplikasiKlinik.perawat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class perawatController {
    
    @GetMapping("/perawat")
    public String homePagePerawat(){
        return "/perawat/antriancheckup";
    }
}
