package Kelompok2_RPL.AplikasiKlinik.register;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Register {
    @NotNull
    private String nama;
    @NotNull
    private String email;
    @NotNull
    @Size(min=4, max=60)
    private String password;
    @Size(min=4, max=60)
    private String RePassword;
    @NotNull
    private LocalDate tanggalLahir;
    @NotNull
    private String no_Hp;
    @NotNull
    private String alamat;
    private String jenis_kelamin;
}
