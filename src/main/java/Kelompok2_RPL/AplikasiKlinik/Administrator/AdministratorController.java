package Kelompok2_RPL.AplikasiKlinik.Administrator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import Kelompok2_RPL.AplikasiKlinik.User.RequiredRole;

@Controller
public class AdministratorController {

    @GetMapping("/administrator/home")
    @RequiredRole("administrator")
    public String homePage() {
        return "Administrator/home"; 
    }
}