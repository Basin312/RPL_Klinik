package Kelompok2_RPL.AplikasiKlinik.home;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Home {
    private int id_pasien;
    private String hari;
    private String namaDokter;
    private Integer nomor_antrian;
    private boolean isCheckup;
    private boolean isDaftar;
}