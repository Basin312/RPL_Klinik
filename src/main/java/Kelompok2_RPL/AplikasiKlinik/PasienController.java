package Kelompok2_RPL.AplikasiKlinik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PasienController {
    
    @Autowired
    private PasienService pasienService;

    @GetMapping("/administrator/daftarulang")
    public String daftarUlang(Model model) {
        List<Pasien> pasienList = pasienService.getAllPasienWithStatus();
        model.addAttribute("pasienList", pasienList);
        return "administrator/daftarulang";
    }

    // Halaman detail pasien
    @GetMapping("/administrator/detailPasien")
    public String detailPasien(@RequestParam("id") int id, Model model) {
        Pasien pasien = pasienService.getPasienById(id);
        model.addAttribute("pasien", pasien);
        return "administrator/detailPasien";
    }

    // Perbarui status pasien
    @PostMapping("/updateStatus")
    @ResponseBody
    public String updateStatus(@RequestParam("id") int id, @RequestParam("status") String status) {
        boolean isDaftar = status.equalsIgnoreCase("Berhasil");
        pasienService.updatePasienStatus(id, isDaftar);
        return "Status berhasil diperbarui";
    }
}
