package Kelompok2_RPL.AplikasiKlinik;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    private String tanggalLahir;
    @NotNull
    private String nomorTlp;
    @NotNull
    private String alamat;
    
    private String jenis;
}
