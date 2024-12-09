package Kelompok2_RPL.AplikasiKlinik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Kelompok2_RPL.AplikasiKlinik.User.Admin.Admin;
import Kelompok2_RPL.AplikasiKlinik.User.Admin.AdminService;
import Kelompok2_RPL.AplikasiKlinik.User.Administrator.Administrator;
import Kelompok2_RPL.AplikasiKlinik.User.Administrator.AdministratorService;
import Kelompok2_RPL.AplikasiKlinik.User.Dokter.Dokter;
import Kelompok2_RPL.AplikasiKlinik.User.Dokter.DokterService;
import Kelompok2_RPL.AplikasiKlinik.User.Pasien.PasienService;
import Kelompok2_RPL.AplikasiKlinik.User.Perawat.Perawat;
import Kelompok2_RPL.AplikasiKlinik.User.Perawat.PerawatService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private DokterService dokterService;

    @Autowired 
    private PasienService pasienService;

    @Autowired
    private PerawatService perawatService;
    
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private AdminService adminService;
    
    @GetMapping("/")
    public String roleslogin(){
        return "MainPage";
    }
    @GetMapping("/LoginPasien")
    public String LoginPasien(){
        return "loginPasien";
    }

    // @PostMapping("/loginPasien")
    // public String loginPasien(@RequestParam String username, @RequestParam String password, HttpSession session){
    //     Pasien user = pasienService.loginPasien(username, password);
    //     if(user==null){
    //         return "redirect:/loginPasien";
    //     }
    //     session.setAttribute("username", user);
    //         return "redirect:/dashboard";   //edit loc
    // }

    @GetMapping("/loginDokter")
    public String loginDokter(){
        return "LoginDokter";
    }
    @PostMapping("/LoginDokter")
    public String loginDokter(@RequestParam String username, @RequestParam String password, HttpSession session){
        Dokter user = dokterService.loginDokter(username, password);
        if(user==null){
            return "redirect:/LoginDokter";
        }
        session.setAttribute("username", user);
            return "redirect:/dashboard";   //edit loc
    }

    @GetMapping("/loginPerawat")
    public String loginPerawat(){
        return "LoginPerawat";
    }
    @PostMapping("/loginPerawat")
    public String loginPerawat(@RequestParam String username, @RequestParam String password, HttpSession session){
        Perawat user = perawatService.loginPerawat(username, password);
        if(user==null){
            return "redirect:/LoginPerawat";
        }
        session.setAttribute("username", user);
            return "redirect:/dashboard";   //edit loc
    }
    @GetMapping("/loginAdministrator")
    public String loginAdministrator(){
        return "LoginAdministrator";
    }
    @PostMapping("/loginAdministrator")
    public String loginAdministrator(@RequestParam String username, @RequestParam String password, HttpSession session){
        Administrator user = administratorService.loginAdministrator(username, password);
        if(user==null){
            return "redirect:/LoginAdministrator";
        }
        session.setAttribute("username", user);
            return "redirect:/dashboard";   //edit loc
    }

    @GetMapping("/loginAdmin")
    public String loginAdmin(){
        return "LoginAdmin";
    }
    @PostMapping("/loginAdmin")
    public String loginAdmin(@RequestParam String username, @RequestParam String password, HttpSession session){
        Admin user = adminService.loginAdmin(username, password);
        if(user==null){
            return "redirect:/LoginAdmin";
        }
        session.setAttribute("username", user);
            return "redirect:/dashboard";   //edit loc
    }
    @GetMapping("/dashboard") //edit loc
    @ResponseBody
    public String index(HttpSession session){
        Dokter user = (Dokter) session.getAttribute("username");
        if(user==null){
            return "redirect:/LoginDokter";
        }
        return "dashboard";
    }
    
}
