package Kelompok2_RPL.AplikasiKlinik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Kelompok2_RPL.AplikasiKlinik.User.LoginPage.Login;
import Kelompok2_RPL.AplikasiKlinik.User.LoginPage.LoginService;
import jakarta.servlet.http.HttpSession;

@Controller
@EnableAspectJAutoProxy
public class LoginController {
    @Autowired 
    private LoginService users;

    
    @GetMapping("/")
    public String roleslogin(){
        return "/login/MainPage";
    }
    @GetMapping("/loginPasien")
    public String LoginPasien(){
        return "/login/loginPasien";
    }

    @PostMapping("/loginPasien")
    public String LoginPasien(@RequestParam String Email, @RequestParam String password, HttpSession session){
        Login user = users.loginPasien(Email, password);
        if(user==null){
            return "redirect:/loginPasien";
        }
        session.setAttribute("Email", user);
            return "redirect:/pasien";   //edit loc
    }

    @GetMapping("/loginDokter")
    public String loginDokter(){
        return "/login/LoginDokter";
    }

    @PostMapping("/loginDokter")
    public String loginDokter(@RequestParam String Email, @RequestParam String password, HttpSession session){
        Login user = users.loginDokter(Email, password);
        if(user==null){
            return "redirect:/LoginDokter";
        }
        session.setAttribute("Email", user);
            return "redirect:/dokter";   //edit loc
    }

    @GetMapping("/loginPerawat")
    public String loginPerawat(){
        return "/login/LoginPerawat";
    }
    @PostMapping("/loginPerawat")
    public String loginPerawat(@RequestParam String Email, @RequestParam String password, HttpSession session){
        Login user = users.loginPerawat(Email, password);
        if(user==null){
            return "redirect:/loginPerawat";
        }
        session.setAttribute("Email", user);
            return "redirect:/perawat";   //edit loc
    }
    @GetMapping("/loginAdministrator")
    public String loginAdministrator(){
        return "/login/LoginAdministrator";
    }
    @PostMapping("/loginAdministrator")
    public String loginAdministrator(@RequestParam String Email, @RequestParam String password, HttpSession session){
        Login user = users.loginAdministrator(Email, password);
        if(user==null){
            return "redirect:/loginAdministrator";
        }
        session.setAttribute("Email", user);
            return "redirect:/administrator/home";   //edit loc
    }

    @GetMapping("/loginAdmin")
    public String loginAdmin(){
        return "/login/LoginAdmin";
    }
    @PostMapping("/loginAdmin")
    public String loginAdmin(@RequestParam String Email, @RequestParam String password, HttpSession session){
        Login user = users.loginAdmin(Email, password);
        if(user==null){
            return "redirect:/loginAdmin";
        }
        session.setAttribute("Email", user);
            return "redirect:/admin";   //edit loc
    }
}

