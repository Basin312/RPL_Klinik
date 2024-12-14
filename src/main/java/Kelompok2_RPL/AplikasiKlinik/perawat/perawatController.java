package Kelompok2_RPL.AplikasiKlinik.perawat;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Kelompok2_RPL.AplikasiKlinik.pasien.Pasien;

@Controller
public class perawatController {
    @Autowired
    private PerawatRepository jdbc;

   
    
    @GetMapping("/perawat")
    public String homePagePerawat(Model model ){
        List<CheckUp> checkups = jdbc.findAll();
        System.out.println( "data :"+checkups.isEmpty());
        model.addAttribute("rell", checkups);
        return "/perawat/antriancheckup";
    }

    // @PostMapping("/checkup")
    // public String halamanCheckup(@RequestParam(required = true) Integer id, @RequestParam(required = true)String date, Model model ){
    //     Optional<Pasien> result = jdbc.findById(id);
    //     Date tanggal= (Date)result.get().getTanggalLahir();
    //     model.addAttribute("nama", result.get().getNama());
    //     model.addAttribute("gender", result.get().getJenis_kelamin());
    //     model.addAttribute("umur", calculateAge(tanggal));
    //     model.addAttribute("tanggal", date);
    //     model.addAttribute("id", id);
    //     return "/perawat/checkup";
    // }
    
    @PostMapping("/checkup")
    public String halamanCheckup(@RequestParam(required = true) Integer id, @RequestParam(required = true) String date, Model model) {
        Optional<Pasien> result = jdbc.findById(id);
        if (result.isPresent()) {
            Pasien pasien = result.get();
            Date tanggal = (Date) pasien.getTanggalLahir();
            model.addAttribute("nama", pasien.getNama());
            model.addAttribute("gender", pasien.getJenis_kelamin());
            model.addAttribute("umur", calculateAge(tanggal));
            model.addAttribute("tanggal", date);
            model.addAttribute("id", id);
        } else {
            model.addAttribute("error", "Data pasien tidak ditemukan");
        }
        return "/perawat/checkup";
    }
    
    @PostMapping("/konfirm")
    public String bikinCheckup(
        @RequestParam(name = "berat") double berat, 
        @RequestParam(name = "tinggi") double tinggi, 
        @RequestParam(name = "darah") String darah, 
        @RequestParam(name = "tanggal") String tanggal, 
        @RequestParam(name ="id") int id){

        System.out.println("berat:" +berat);
        System.out.println("tinggi :"+ tinggi);
        System.out.println("darah :"+darah);
        System.out.println("tanggal :"+tanggal);
        System.out.println("id :"+id);

        jdbc.makeCheckup(tinggi, berat, darah, tanggal, id);
        return "redirect:/perawat";
    }
     // Helper method to calculate age
     private int calculateAge(Date birthDate) {
        if (birthDate == null) {
            return 0; // Default value if birthDate is null
        }
        // Convert java.sql.Date to LocalDate
        LocalDate birthLocalDate = birthDate.toLocalDate();
        return Period.between(birthLocalDate, LocalDate.now()).getYears();
    }
}
