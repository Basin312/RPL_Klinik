package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailPasienDTO {
    private String nama;
    private Date tanggalPendaftaran;
    private String noHp;
    private String jenisKelamin;
    private String namaDokter;
    private String jamKonsul;
}
