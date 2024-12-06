package Kelompok2_RPL.AplikasiKlinik;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Pasien {
    @NotNull
    private String nama;
    @NotNull
    private String email;
    @NotNull
    @Size(min=4, max=60)
    private String password;
    @NotNull
    @Size(min=4, max=60)
    private String rePassword;
    @NotNull
    private LocalDate tanggalLahir;
    @NotNull
    private String nomorTlp;
    private String alamat;
    private String jenis;
}
