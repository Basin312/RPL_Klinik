package Kelompok2_RPL.AplikasiKlinik.catatan_obat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatatanObat {
    private int id_Obat;
    private String obat;
    private String dosis;
    private Date tanggal;
    private int id_Konsul;

    // id_Obat SERIAL PRIMARY KEY,
    // obat VARCHAR(50), --nama obatnya, cth kaya obat pilek, ini diketik dokternya
    // dosis VARCHAR(50), --dosis penggunaan obatnya, cth 1 hari 2 kali minum
    // tanggal DATE DEFAULT CURRENT_DATE, --tanggal dimasukan data
    // id_Konsul int REFERENCES Konsultasi(id_Konsul)
}
