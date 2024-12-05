package Kelompok2_RPL.AplikasiKlinik;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
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
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Match the input type="date" format
    private LocalDate tanggalLahir;
    @NotNull
    private String nomorTlp;
    
    private String jenis;
}
