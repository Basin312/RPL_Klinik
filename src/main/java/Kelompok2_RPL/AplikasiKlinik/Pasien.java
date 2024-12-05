package Kelompok2_RPL.AplikasiKlinik;


import java.util.Date;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pasien {
    @NotNull
    private int id_Pasien;
    @NotNull
    private String nama;
    @NotNull
    private String email;
    @NotNull
    @Size(min=4, max=60)
    private String password;
    @NotNull
    private Date tanggalLahir;
    @NotNull
    private String jenis_kelamin;
    @NotNull
    private String no_hp;
    @NotNull
    private String alamat;
    
    private String status;


}
