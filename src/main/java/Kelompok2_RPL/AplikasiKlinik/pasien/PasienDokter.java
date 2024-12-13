package Kelompok2_RPL.AplikasiKlinik.pasien;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasienDokter {
    private String nama;
    private int umur;
    private String jenis_kelamin;
    private LocalDate tanggal_lahir;
}
