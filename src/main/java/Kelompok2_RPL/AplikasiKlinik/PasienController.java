package Kelompok2_RPL.AplikasiKlinik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasienController {
    
    @Autowired
    private PasienService pasienService;

    @GetMapping("/administrator/daftarulang")
    public String daftarUlang(Model model) {
        List<Pasien> pasienList = pasienService.getAllPasienWithStatus();
        model.addAttribute("pasienList", pasienList);
        return "/administrator/daftarulang";
    }
}
