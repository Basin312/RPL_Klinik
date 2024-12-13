package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Kelompok2_RPL.AplikasiKlinik.User.RequiredRole;


@Controller
public class DaftarUlangController {
    
    @Autowired
    private DaftarUlangService daftarUlangService;

    @RequiredRole("administrator")
    @GetMapping("/administrator/daftarulang")
    public String daftarUlang(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<PasienStatusDTO> pasienList;

        if (keyword != null && !keyword.isEmpty()) {
            pasienList = daftarUlangService.searchPasienByName(keyword);
        }
        else {
            pasienList = daftarUlangService.getAllPasienWithStatus();
        }
        
        model.addAttribute("pasienList", pasienList);
        model.addAttribute("keyword", keyword);
        return "administrator/daftarulang";
    }

    // Halaman detail pasien
    @GetMapping("/administrator/detailPasien")
    @RequiredRole("administrator")
    public String detailPasien(@RequestParam("id") int id, Model model) {
        DetailPasienDTO detailPasien = daftarUlangService.getDetailPasienById(id);
        if (detailPasien == null) {
            throw new RuntimeException("Data pasien tidak ditemukan untuk ID: " + id);
        }
        model.addAttribute("pasien", detailPasien);
        return "administrator/detailPasien";
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("id") int id, @RequestParam("status") String status) {
        daftarUlangService.updatePasienStatus(id, status);
        return "redirect:/administrator/daftarulang";
    }
    
}
