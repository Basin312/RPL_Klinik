package Kelompok2_RPL.AplikasiKlinik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    
    @GetMapping("/admin")
    public String HomeAdmin(){
        return "/Admin/HomeAdmin";
    }

    @GetMapping("/addPegawai")
    public String TambahPegawai(){
        return "/Admin/Pegawai/TambahPegawai";
    }

    @GetMapping("/listdokter")
    public String listDokter(){
        return "/Admin/ListDokter/ListDokter";
    }
    @GetMapping("/listperawat")
    public String listPerawat(){
        return "/Admin/ListPerawat/ListPerawat";
    }
    @GetMapping("/listadministrator")
    public String listAdministrator(){
        return "/Admin/ListAdministrator/ListAdministrator";
    }
}
