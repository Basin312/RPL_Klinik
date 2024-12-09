package Kelompok2_RPL.AplikasiKlinik.User.Dokter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dokter {
    @NotNull
    @Size(min=4, max =30)
    private String username;

    @NotNull
    @Size(min=4, max = 30)
    private String password;

}
