package Kelompok2_RPL.AplikasiKlinik.jadwal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JadwalController {

    @Autowired
    private JadwalService jadwalService;

    @GetMapping("/jadwal")
    public String halamanListJadwal(Model model) {
        model.addAttribute("jadwalList", jadwalService.getAllJadwal());
        model.addAttribute("dokterList", jadwalService.getAllDokter());
        model.addAttribute("specialisList", jadwalService.getAllSpecialis());
        return "/jadwal";
    }
}
