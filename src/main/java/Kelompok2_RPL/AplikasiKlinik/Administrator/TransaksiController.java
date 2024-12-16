package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransaksiController {
    
    @Autowired
    private TransaksiService transaksiService;

    @GetMapping("/administrator/transaksi")
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

    @GetMapping("/administrator/detailTransaksi")
    public String detailTransaksi(@RequestParam("id") int idPasien, Model model) {
        DetailTransaksiDTO detailTransaksi = transaksiService.getDetailTransaksi(idPasien);
        model.addAttribute("transaksi", detailTransaksi);
        model.addAttribute("isReadOnly", detailTransaksi.isReadOnly());
        return "administrator/detailTransaksi";
    }

    @PostMapping("/updateTransaksiStatus")
    public String updateTransaksiStatus(@RequestParam("id") int idPasien, @RequestParam("methodBayar") String methodBayar, @RequestParam("status") String status) {
        transaksiService.updateTransaksiByPasien(idPasien, status, methodBayar);
        return "redirect:/administrator/transaksi";
    }
}