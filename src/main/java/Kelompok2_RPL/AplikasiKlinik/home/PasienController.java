package Kelompok2_RPL.AplikasiKlinik.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Kelompok2_RPL.AplikasiKlinik.User.LoginPage.Login;
import jakarta.servlet.http.HttpSession;

@Controller
public class PasienController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/pasien")
    public String halamanPasien(HttpSession session, Model model) {
        Login user = (Login) session.getAttribute("Email");
        if (user == null) {
            return "redirect:/loginPasien"; // Redirect if not logged in
        }

        int idPasien = user.getId(); // Get id_pasien directly
        Home home = homeService.getNomorAntrian(idPasien); // Fetch nomorantrian
        model.addAttribute("home", home);
        return "pasien";
    }
    
    @GetMapping("/logout")
        public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPasien";
    }
}