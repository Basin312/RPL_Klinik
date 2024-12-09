package Kelompok2_RPL.AplikasiKlinik.User.Pasien;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pasien {
    @NotNull
    @Size(min=4, max =30)
    private String username;

    @NotNull
    @Size(min=4, max = 30)
    private String password;
}
