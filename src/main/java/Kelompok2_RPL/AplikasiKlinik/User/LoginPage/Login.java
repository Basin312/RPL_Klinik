package Kelompok2_RPL.AplikasiKlinik.User.LoginPage;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @NotNull
    @Size(min=4, max =30)
    private String email;

    @NotNull
    @Size(min=4, max = 30)
    private String password;

    private String roles;
    
    private int id;

    public Login(String email, String password, int id) {
        this.email = email;
        this.password = password;
        this.id = id;
    }
}

