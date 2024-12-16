package Kelompok2_RPL.AplikasiKlinik.dokter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dokter {
    private int id_Dokter;
    private String name;
    private String email;
    private String password;
    private Boolean status;
    private int id_Spesialis;
}
