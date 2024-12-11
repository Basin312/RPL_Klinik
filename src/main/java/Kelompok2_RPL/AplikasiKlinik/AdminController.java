package Kelompok2_RPL.AplikasiKlinik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    
    @GetMapping("/admin")
    public String HomeAdmin(){
        return "/Admin/HomeAdmin";
    }
}
