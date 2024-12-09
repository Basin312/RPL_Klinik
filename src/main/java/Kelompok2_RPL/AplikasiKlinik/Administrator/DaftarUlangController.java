package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DaftarUlangController {
    
    @Autowired
    private DaftarUlangService daftarUlangService;

    @GetMapping("/administrator/daftarulang")
    public String daftarUlang(Model model) {
        List<PasienStatusDTO> pasienList = daftarUlangService.getAllPasienWithStatus();
        model.addAttribute("pasienList", pasienList);
        return "administrator/daftarulang";
    }

    // Halaman detail pasien
    @GetMapping("/administrator/detailPasien")
    public String detailPasien(@RequestParam("id") int id, Model model) {
        DetailPasienDTO detailPasien = daftarUlangService.getDetailPasienById(id);
        if (detailPasien == null) {
            throw new RuntimeException("Data pasien tidak ditemukan untuk ID: " + id);
        }
        model.addAttribute("pasien", detailPasien);
        return "administrator/detailPasien";
    }

    
}
