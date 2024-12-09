package Kelompok2_RPL.AplikasiKlinik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Kelompok2_RPL.AplikasiKlinik.User.LoginPage.Login;
import Kelompok2_RPL.AplikasiKlinik.User.LoginPage.LoginService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired 
    private LoginService users;

    
    @GetMapping("/")
    public String roleslogin(){
        return "MainPage";
    }
    @GetMapping("/loginPasien")
    public String LoginPasien(){
        return "loginPasien";
    }

    @PostMapping("/loginPasien")
    public String LoginPasien(@RequestParam String Email, @RequestParam String password, HttpSession session){
        Login user = users.loginPasien(Email, password);
        if(user==null){
            return "redirect:/loginPasien";
        }
        session.setAttribute("Email", user);
            return "redirect:/dashboard";   //edit loc
    }

    @GetMapping("/loginDokter")
    public String loginDokter(){
        return "LoginDokter";
    }

    @PostMapping("/loginDokter")
    public String loginDokter(@RequestParam String Email, @RequestParam String password, HttpSession session){
        Login user = users.loginDokter(Email, password);
        if(user==null){
            return "redirect:/LoginDokter";
        }
        session.setAttribute("Email", user);
            return "redirect:/dashboard";   //edit loc
    }

    @GetMapping("/loginPerawat")
    public String loginPerawat(){
        return "LoginPerawat";
    }
    @PostMapping("/loginPerawat")
    public String loginPerawat(@RequestParam String Email, @RequestParam String password, HttpSession session){
        Login user = users.loginPerawat(Email, password);
        if(user==null){
            return "redirect:/loginPerawat";
        }
        session.setAttribute("Email", user);
            return "redirect:/dashboard";   //edit loc
    }
    @GetMapping("/loginAdministrator")
    public String loginAdministrator(){
        return "LoginAdministrator";
    }
    @PostMapping("/loginAdministrator")
    public String loginAdministrator(@RequestParam String Email, @RequestParam String password, HttpSession session){
        Login user = users.loginAdministrator(Email, password);
        if(user==null){
            return "redirect:/loginAdministrator";
        }
        session.setAttribute("Email", user);
            return "redirect:/dashboard";   //edit loc
    }

    @GetMapping("/loginAdmin")
    public String loginAdmin(){
        return "LoginAdmin";
    }
    @PostMapping("/loginAdmin")
    public String loginAdmin(@RequestParam String Email, @RequestParam String password, HttpSession session){
        Login user = users.loginAdmin(Email, password);
        if(user==null){
            return "redirect:/loginAdmin";
        }
        session.setAttribute("Email", user);
            return "redirect:/dashboard";   //edit loc
    }

    @GetMapping("/dashboard") //edit loc
    @ResponseBody
    public String index(HttpSession session){
        Login user = (Login)session.getAttribute("email");
        if(user==null){
            return "redirect:/LoginDokter";
        }
        return "dashboard";
    }
}

