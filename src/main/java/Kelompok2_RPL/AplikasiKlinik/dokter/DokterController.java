package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Kelompok2_RPL.AplikasiKlinik.catatan_obat.CatatanObat;
import Kelompok2_RPL.AplikasiKlinik.checkup.Checkup;
import Kelompok2_RPL.AplikasiKlinik.dokumen_pendukung.DokumenPendukung;
import Kelompok2_RPL.AplikasiKlinik.konsultasi.Konsultasi;
import Kelompok2_RPL.AplikasiKlinik.pasien.PasienDokter;
import Kelompok2_RPL.AplikasiKlinik.User.RequiredRole;

import Kelompok2_RPL.AplikasiKlinik.pendaftaran.PendaftaranKonsultasi;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/dokter")
public class DokterController {
    @Autowired
    private DokterService dokterService;
    
    @Autowired
    private HttpSession session;
    
    @GetMapping("/home")
    @RequiredRole("dokter")
    public String getIndex(Model model, @RequestParam(name = "filter", required = false) String filter){
        if(filter == null){ //ga pake filter
            List<PendaftaranKonsultasi> listAntrian = this.dokterService.getAllPendaftaran((int) session.getAttribute("id_Dokter"));
            model.addAttribute("listAntrian", listAntrian);
            if(listAntrian.size() == 0){
                model.addAttribute("nomor_antrian", null);
            }else{
                model.addAttribute("nomor_antrian", listAntrian.get(0).getNomor_antrian());
            }
        }else{
            List<PendaftaranKonsultasi> listAntrian = this.dokterService.searchPendaftaranByName((int) session.getAttribute("id_Dokter"), filter);
            model.addAttribute("listAntrian", listAntrian);
            model.addAttribute("filter", filter);
            if(listAntrian.size() == 0){
                model.addAttribute("nomor_antrian", null);
            }else{
                model.addAttribute("nomor_antrian", listAntrian.get(0).getNomor_antrian());
            }
        }
        return "/dokter/index";
    }

    @GetMapping("/konsultasi/{id_Pasien}")
    @RequiredRole("dokter")
    public String konsultasi(@PathVariable("id_Pasien") int id_Pasien, Model model, @RequestParam(name = "id_Pendaftaran", required = false) Integer id_Pendaftaran, Konsultasi konsultasi, CatatanObat catatanObat){
        //masukin id_Pendaftaran ke session saat akses pertama kali dari mencet button di home
        if(id_Pendaftaran != null){
            session.setAttribute("id_Pendaftaran", id_Pendaftaran.intValue());
        }
        
        //dapetin identitas_pasien
        Optional<PasienDokter> pasien = this.dokterService.getPasienById(id_Pasien);
        if (pasien.isPresent()) {
            model.addAttribute("nama", pasien.get().getNama());
            model.addAttribute("umur", pasien.get().getUmur());
            model.addAttribute("jenis_kelamin", pasien.get().getJenis_kelamin());
            model.addAttribute("tanggal_lahir", pasien.get().getTanggal_lahir().toString());
        } else {
            // Jika pasien tidak ditemukan, lempar error atau alihkan ke halaman lain
            return "error/404";
        }

        //dapetin data_checkup di hari itu
        Optional<Checkup> checkup = this.dokterService.getCheckupByPasienAndCurrDate(id_Pasien);
        if (checkup.isPresent()) {
            model.addAttribute("berat", checkup.get().getBerat());
            model.addAttribute("tinggi", checkup.get().getTinggi());
            model.addAttribute("tekanan_darah", checkup.get().getTekanan_darah());
            session.setAttribute("id_Checkup", checkup.get().getId_Checkup());
        }else{
            model.addAttribute("berat", "Belum melakukan checkup");
        }
        
        return "/dokter/konsultasi";
    }

    @PostMapping(value = "/konsultasi/{id_Pasien}", params = "form=diagnosa")
    public String inputDiagnosa(@RequestParam(name = "diagnosa", required = true) String diagnosa, @PathVariable("id_Pasien") int id_Pasien, @Valid Konsultasi konsultasi, BindingResult bindingResult, CatatanObat catatanObat, Model model){
        Optional<PasienDokter> pasien = this.dokterService.getPasienById(id_Pasien);
        model.addAttribute("nama", pasien.get().getNama());
        model.addAttribute("umur", pasien.get().getUmur());
        model.addAttribute("jenis_kelamin", pasien.get().getJenis_kelamin());
        model.addAttribute("tanggal_lahir", pasien.get().getTanggal_lahir().toString());

        Optional<Checkup> checkup = this.dokterService.getCheckupByPasienAndCurrDate(id_Pasien);
        model.addAttribute("berat", checkup.get().getBerat());
        model.addAttribute("tinggi", checkup.get().getTinggi());
        model.addAttribute("tekanan_darah", checkup.get().getTekanan_darah());
        
        if(bindingResult.hasErrors()){
            return "/dokter/konsultasi";
        }

        if(session.getAttribute("id_Konsul") != null){
            bindingResult.rejectValue("diagnosa", "DiagnosaSudahAda", "Diagnosa hanya bisa diisi sekali");
            return "/dokter/konsultasi";
        }

        //masukin ke konsultasi
        this.dokterService.tambahKonsultasi(diagnosa, (int) session.getAttribute("id_Dokter"), (int)session.getAttribute("id_Checkup"));

        //dapetin konsultasi yang baru dimasukin
        Optional<Konsultasi> latestKonsultasi = this.dokterService.getKonsultasiTerbaruByDokterAndCurrDate((int) session.getAttribute("id_Dokter"));
        session.setAttribute("id_Konsul", latestKonsultasi.get().getId_Konsul().intValue());

        return "redirect:/dokter/konsultasi/" + id_Pasien; //balik lagi ke halaman konsultasi
    }

    @PostMapping(value = "/konsultasi/{id_Pasien}", params = "form=obat")
    public String inputObat(@RequestParam(name = "obat", required = true) String obat, @RequestParam(name = "dosis", required = true) String dosis, @PathVariable("id_Pasien") int id_Pasien, @Valid CatatanObat catatanObat, BindingResult bindingResult, Konsultasi konsultasi, Model model){
        Optional<PasienDokter> pasien = this.dokterService.getPasienById(id_Pasien);
        model.addAttribute("nama", pasien.get().getNama());
        model.addAttribute("umur", pasien.get().getUmur());
        model.addAttribute("jenis_kelamin", pasien.get().getJenis_kelamin());
        model.addAttribute("tanggal_lahir", pasien.get().getTanggal_lahir().toString());

        Optional<Checkup> checkup = this.dokterService.getCheckupByPasienAndCurrDate(id_Pasien);
        model.addAttribute("berat", checkup.get().getBerat());
        model.addAttribute("tinggi", checkup.get().getTinggi());
        model.addAttribute("tekanan_darah", checkup.get().getTekanan_darah());

        if(bindingResult.hasErrors()){
            return "/dokter/konsultasi";
        }

        if(session.getAttribute("id_Konsul") == null){
            bindingResult.rejectValue("dosis", "DiagnosaBelumAda", "Tolong masukan diagnosa terlebih dahulu");
            return "/dokter/konsultasi";
        }
        
        //tambah obat
        this.dokterService.tambahObat(obat, dosis, (int)session.getAttribute("id_Konsul"));

        return "redirect:/dokter/konsultasi/" + id_Pasien; //balik lagi ke halaman konsultasi
    }

    @GetMapping("/rekamMedis/{id_Pasien}")
    @RequiredRole("dokter")
    public String showRekamMedisPasien(@PathVariable("id_Pasien") int id_Pasien, Model model){
        //dapetin identitas_pasien
        Optional<PasienDokter> pasien = this.dokterService.getPasienById(id_Pasien);
        if (pasien.isPresent()) {
            model.addAttribute("nama", pasien.get().getNama());
            model.addAttribute("umur", pasien.get().getUmur());
            model.addAttribute("jenis_kelamin", pasien.get().getJenis_kelamin());
            model.addAttribute("tanggal_lahir", pasien.get().getTanggal_lahir().toString());
        } else {
            // Jika pasien tidak ditemukan, lempar error atau alihkan ke halaman lain
            return "error/404";
        }

        //dapetin list checkup
        List<Checkup> listCheckup = this.dokterService.getCheckupByPasien(id_Pasien);
        model.addAttribute("listCheckup", listCheckup);

        //dapetin list diagnosa
        List<Konsultasi> listDiagnosa = this.dokterService.getKonsultasiByPasien(id_Pasien);
        model.addAttribute("listDiagnosa", listDiagnosa);

        //dapetin list dokumen
        List<DokumenPendukung> listDokumen = this.dokterService.getDokumenPendukungByPasien(id_Pasien);
        model.addAttribute("listDokumen", listDokumen);

        //dapetin list obat
        List<CatatanObat> listObat = this.dokterService.getCatatanObatByPasien(id_Pasien);
        model.addAttribute("listObat", listObat);
        
        return "/dokter/rekamMedis";
    }

    
    @GetMapping("/dokumenPendukung/{id_Dokumen}")
    @RequiredRole("dokter")
    public ResponseEntity<Resource> showDokumen(@PathVariable("id_Dokumen") int id_Dokumen){
        // Dapatkan path file dari database
    public ResponseEntity<Resource> showDokumen(@PathVariable("id_Dokumen") int id_Dokumen) {
        Optional<DokumenPendukung> dokumen = dokterService.getDokumenById(id_Dokumen);
        if (dokumen.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String filePath = "static" + dokumen.get().getFile_dokumen();

        try {
            // Load resource from classpath
            ClassPathResource resource = new ClassPathResource(filePath);
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/simpanKonsultasi/{id_Pasien}")
    public String simpanKonsultasi(@PathVariable("id_Pasien") int id_Pasien){
        if(session.getAttribute("id_Konsul") == null){
            return "redirect:/dokter/konsultasi/" + id_Pasien;
        }
        
        //set is_Konsul jadi true
        this.dokterService.setIsKonsul((int) session.getAttribute("id_Pendaftaran"));

        //hapusin attribute di session
        session.removeAttribute("id_Pendaftaran");
        session.removeAttribute("id_Checkup");
        session.removeAttribute("id_Konsul");


        return "redirect:/dokter/home";
    }

    @GetMapping("/logoutdokter")
    public String logout(){
        session.invalidate();

        return "redirect:/loginDokter";
    }
}

/*
SESSION NYIMPEN
-)id_Dokter
-)id_Pendaftaran
-)id_Checkup
-)id_Konsul
 */
