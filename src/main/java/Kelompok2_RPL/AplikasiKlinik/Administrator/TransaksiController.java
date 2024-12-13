package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Kelompok2_RPL.AplikasiKlinik.User.RequiredRole;

@Controller
public class TransaksiController {
    
    @Autowired
    private TransaksiService transaksiService;
    
    @GetMapping("/administrator/transaksi")
    @RequiredRole("administrator")
    public String transaksi(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<PasienStatusDTO> pasienList;

        if (keyword != null && !keyword.isEmpty()) {
            pasienList = transaksiService.searchPasienByName(keyword);
        }
        else {
            pasienList = transaksiService.getAllPasienWithStatus();
        }
        
        model.addAttribute("pasienList", pasienList);
        model.addAttribute("keyword", keyword);
        return "administrator/transaksi";
    }
}
