package Kelompok2_RPL.AplikasiKlinik.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Kelompok2_RPL.AplikasiKlinik.Administrator.Pasien;
import jakarta.validation.Valid;

@Controller
public class RegistrasiController {
    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String halamanRegister(Register register){
        return "registerPasien";
    }
    
    @PostMapping("/register")
    public String registerData(@Valid Register register, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "registerPasien";
        }
        System.out.println("Tanggal Lahir: " + register.getTanggalLahir());
        if(!register.getPassword().equals(register.getRePassword())){
            bindingResult.rejectValue("confirmpassword", "PasswordMissmatch","Passwords do not match");
            return "registerPasien";
        }

        if(!registerService.register(register)){
            bindingResult.rejectValue("nama", "inputInvalid", "your data is invalid");
            return "registerPasien";
        }

        return "redirect:/result";
    }

    @GetMapping("/result")
    public String resultView(){
        return "result";
    }
}
