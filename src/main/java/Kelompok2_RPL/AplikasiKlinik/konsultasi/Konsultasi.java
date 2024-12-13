package Kelompok2_RPL.AplikasiKlinik.konsultasi;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Konsultasi {
    private Integer id_Konsul;

    @NotNull
    @Size(min = 4, max = 100)
    private String diagnosa;

    private LocalDate tanggal;
    private Integer id_Dokter;
    private Integer id_Checkup;
    
    /*
    id_Konsul SERIAL PRIMARY KEY,
    diagnosa VARCHAR(100),
	tanggal DATE DEFAULT CURRENT_DATE,
    id_Dokter int REFERENCES Dokter(id_Dokter),
    id_Checkup int REFERENCES Checkup(id_Checkup)
     */
}
