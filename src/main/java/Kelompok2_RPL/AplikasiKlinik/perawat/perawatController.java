package Kelompok2_RPL.AplikasiKlinik.perawat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Kelompok2_RPL.AplikasiKlinik.User.LoginPage.Login;

import Kelompok2_RPL.AplikasiKlinik.dokumen_pendukung.DokumenPendukungRepository;
import Kelompok2_RPL.AplikasiKlinik.pasien.Pasien;
import jakarta.servlet.http.HttpSession;

import Kelompok2_RPL.AplikasiKlinik.User.RequiredRole;

@Controller
public class perawatController {
    @Autowired
    private PerawatRepository jdbc;

    @Autowired
    private DokumenPendukungRepository jdbcDokumen;

    @Autowired
    private HttpSession session;

    // Static "dokumen" folder inside src/main/resources/static
   
    private static final String UPLOAD_DIR = "src/main/resources/static/dokumen";
    
    @GetMapping("/perawat")
    @RequiredRole("perawat")
    public String homePagePerawat(Model model){
        List<CheckUp> checkups = jdbc.findAll();
        List<Pasien> pasiens = jdbc.findPasiens();
        System.out.println( "data :"+checkups.isEmpty());
        model.addAttribute("rell", checkups);
        model.addAttribute("list", pasiens);
    
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
    @RequiredRole("perawat")
    public String halamanCheckup(@RequestParam(required = true) Integer id, @RequestParam(required = true) String date, Model model) {
        Optional<Pasien> result = jdbc.findById(id);
        if (result.isPresent()) {
            Pasien pasien = result.get();
            Date tanggal = (Date) pasien.getTanggal_lahir();
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
        @RequestParam(name ="id") int id, @RequestParam(name = "dokumen")  MultipartFile file){

        System.out.println("berat:" +berat);
        System.out.println("tinggi :"+ tinggi);
        System.out.println("darah :"+darah);
        System.out.println("tanggal :"+tanggal);
        System.out.println("id :"+id);

        jdbc.makeCheckup(tinggi, berat, darah, tanggal, id);
        return "redirect:/perawat";
    }

    @PostMapping("/uploadfile")
    public String uploadFile(
            @RequestParam("file") MultipartFile file, 
            @RequestParam(value = "options", required = false) Integer options, // Value from datalist
            @RequestParam("namafile") String nama,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Check if the file is empty
        if(file.isEmpty() && options==null){
            redirectAttributes.addFlashAttribute("errorUpload", "belum ada data yang  dimasukan");
            return "redirect:/perawat";
        }else if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorUpload", "ga ada file yang akan uploaded. Silahkan masukan file.");
            return "redirect:/perawat";  // Redirect to the page with the form
        }else if(options == null){
            redirectAttributes.addFlashAttribute("errorUpload", "ga ada data pasien untuk dimasukan");
            return "redirect:/perawat";
        }else if(nama.isEmpty()){
            redirectAttributes.addFlashAttribute("errorUpload", "belom masukan nama file");
            return "redirect:/perawat";
        }

        try {
            // Ensure the "dokumen" folder exists
            // System.out.println("create path");
            Path uploadPath = Paths.get(UPLOAD_DIR);
            Files.createDirectories(uploadPath);

            // System.out.println("get filename");
            // Generate a unique filename to avoid overwriting files
            String filename =  UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // System.out.println("set destination");
            // Resolve the file path in "static/dokumen" folder
            Path destFile = uploadPath.resolve(filename);

            // System.out.println("try save file");
            // Save the file to the target path
            Files.copy(file.getInputStream(), destFile);

            // System.out.println("call repository");
            
            //get id user
            Login user = (Login)session.getAttribute("User");
            int id_Perawat = user.getId();
            // jdbcDokumen.addDokumenPendukung(filename, "/dokumen"+filename, LocalDate.now(), options, id_Perawat);
            if(jdbcDokumen.addDokumenPendukung(nama, "/dokumen/"+filename, LocalDate.now(), options, id_Perawat)){
                Pasien pasien = jdbc.findById(options).get();
            // Return success response including datalist input
            redirectAttributes.addFlashAttribute("success", "File uploaded successfully: " + nama);
            redirectAttributes.addFlashAttribute("selectedOption", pasien.getNama()); // Passing selected option to be displayed
            }else{
                redirectAttributes.addFlashAttribute("errorUpload", "gagal upload");
                return "redirect:/perawat";
            }
            
            

        } catch (IOException e) {
            // Return error response if file upload fails
            model.addAttribute("error", "Failed to upload file: " + e.getMessage());
        }
        return "redirect:/perawat";  // Redirect to the page to show the result
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