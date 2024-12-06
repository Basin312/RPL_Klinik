package Kelompok2_RPL.AplikasiKlinik.Administrator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministratorController {

    @GetMapping("/administrator/home")
    public String homePage() {
        return "Administrator/home"; 
    }
}
