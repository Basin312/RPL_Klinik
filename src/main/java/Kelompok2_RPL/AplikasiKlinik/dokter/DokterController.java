package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Kelompok2_RPL.AplikasiKlinik.pendaftaran.PendaftaranKonsultasi;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dokter")
public class DokterController {
    @Autowired
    private DokterService dokterService;
    
    @Autowired
    private HttpSession session;
    
    @GetMapping("/home")
    public String getIndex(Model model, @RequestParam(name = "filter", required = false) String filter){
        if(filter == null){ //ga pake filter
            List<PendaftaranKonsultasi> listAntrian = this.dokterService.getAllPendaftaran((int) session.getAttribute("id_Dokter"));
            model.addAttribute("listAntrian", listAntrian);
            if(listAntrian.size() == 0){
                model.addAttribute("nomorAntrian", null);
            }else{
                model.addAttribute("nomorAntrian", listAntrian.get(0).getNomorAntrian());
            }
        }
        return "/dokter/index";
    }
}
