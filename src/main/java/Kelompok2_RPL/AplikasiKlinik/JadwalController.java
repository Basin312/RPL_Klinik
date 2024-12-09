package Kelompok2_RPL.AplikasiKlinik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


 @Controller
 public class JadwalController {
  
    @GetMapping("/jadwal") //tbcl
    @ResponseBody
    public String halamanListJadwal(){
        return "listJadwal";
    }
 }