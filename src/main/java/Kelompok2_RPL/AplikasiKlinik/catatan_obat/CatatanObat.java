package Kelompok2_RPL.AplikasiKlinik.catatan_obat;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatatanObat {
    private Integer id_Obat;

    @NotNull
    @Size(min = 4, max = 50)
    private String obat;

    @NotNull
    @Size(min = 4, max = 50)
    private String dosis;

    private LocalDate tanggal;
    private Integer id_Konsul;

    // id_Obat SERIAL PRIMARY KEY,
    // obat VARCHAR(50), --nama obatnya, cth kaya obat pilek, ini diketik dokternya
    // dosis VARCHAR(50), --dosis penggunaan obatnya, cth 1 hari 2 kali minum
    // tanggal DATE DEFAULT CURRENT_DATE, --tanggal dimasukan data
    // id_Konsul int REFERENCES Konsultasi(id_Konsul)
}
