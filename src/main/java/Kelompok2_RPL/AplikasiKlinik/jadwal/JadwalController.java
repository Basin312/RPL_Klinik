package Kelompok2_RPL.AplikasiKlinik.jadwal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Kelompok2_RPL.AplikasiKlinik.User.RequiredRole;

@Controller
public class JadwalController {

    @Autowired
    private JadwalService jadwalService;

    @GetMapping("/jadwal")
    @RequiredRole("pasien")
    public String halamanListJadwal(Model model) {
        List<Jadwal> jadwalList = jadwalService.getAllJadwal();
        List<Object[]> jadwalWithDates = jadwalList.stream()
                .map(jadwal -> new Object[]{
                        jadwal, 
                        calculateDate(jadwal.getIdHari())
                    }).collect(Collectors.toList());

        model.addAttribute("jadwalWithDates", jadwalWithDates);
        model.addAttribute("dokterList", jadwalService.getAllDokter());
        model.addAttribute("specialisList", jadwalService.getAllSpecialis());

        return "/jadwal";
    }

    @GetMapping("/jadwal/filterSpecialis")
    @RequiredRole("pasien")
    public String filterBySpecialis(@RequestParam("idSpecialis") int idSpecialis, Model model) {
    List<Jadwal> jadwalList = jadwalService.getJadwalBySpecialisId(idSpecialis);
    List<Object[]> jadwalWithDates = jadwalList.stream()
            .map(jadwal -> new Object[]{
                    jadwal, 
                    DateUtilities.calculateDate(jadwal.getIdHari())
            })
            .collect(Collectors.toList());

    model.addAttribute("jadwalWithDates", jadwalWithDates);
    model.addAttribute("dokterList", jadwalService.getAllDokter());
    model.addAttribute("specialisList", jadwalService.getAllSpecialis());

    return "/jadwal";
}

    @GetMapping("/jadwal/filterDokter")
    @RequiredRole("pasien")
    public String filterByDokter(@RequestParam("idDokter") int idDokter, Model model) {
    List<Jadwal> jadwalList = jadwalService.getJadwalByDokterId(idDokter);
    List<Object[]> jadwalWithDates = jadwalList.stream()
            .map(jadwal -> new Object[]{
                    jadwal, 
                    DateUtilities.calculateDate(jadwal.getIdHari())
            })
            .collect(Collectors.toList());

    model.addAttribute("jadwalWithDates", jadwalWithDates);
    model.addAttribute("dokterList", jadwalService.getAllDokter());
    model.addAttribute("specialisList", jadwalService.getAllSpecialis());

    return "/jadwal";
    }


    private LocalDate calculateDate(int idHari) {
        LocalDate today = LocalDate.now();
        LocalDate targetDate = today.with(DayOfWeek.of(idHari));
        if (targetDate.isBefore(today)) {
            // Move to the next week if the target day has already passed
            targetDate = targetDate.plusWeeks(1);
        }
        return targetDate;
    }
}
