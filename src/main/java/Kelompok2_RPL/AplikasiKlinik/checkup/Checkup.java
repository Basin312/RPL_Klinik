package Kelompok2_RPL.AplikasiKlinik.checkup;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Checkup {
    private int id_Checkup;
    private Double tinggi;
    private Double berat;
    private String tekanan_darah;
    private LocalDate tanggal;
    private int id_Pasien;

    // id_Checkup SERIAL PRIMARY KEY,
    // tinggi DECIMAL(3,2),
    // berat DECIMAL(3,2),
    // tekanan_darah VARCHAR(10),
	// tanggal DATE DEFAULT CURRENT_DATE,
    // id_Pasien int REFERENCES Pasien(id_Pasien)
}
