package Kelompok2_RPL.AplikasiKlinik.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Kelompok2_RPL.AplikasiKlinik.User.RequiredRole;
import Kelompok2_RPL.AplikasiKlinik.User.LoginPage.Login;
import jakarta.servlet.http.HttpSession;

@Controller
public class PasienController {

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private HttpSession session;

    @GetMapping("/pasien")
    @RequiredRole("pasien")
    public String halamanPasien(HttpSession session, Model model) {
        Login user = (Login)session.getAttribute("User");
        List<Home> data= homeRepository.listPendaftaran(user.getId());

        model.addAttribute("row", data);
        return "pasien";
    }
    

    @GetMapping("/logoutpasien")
        public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPasien";
    }

}