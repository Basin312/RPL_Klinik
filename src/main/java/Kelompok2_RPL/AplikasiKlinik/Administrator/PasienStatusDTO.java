package Kelompok2_RPL.AplikasiKlinik.Administrator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasienStatusDTO {
    private int idPasien;
    private String nama;
    private String status;
}