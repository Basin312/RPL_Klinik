package Kelompok2_RPL.AplikasiKlinik.konsultasi;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Konsultasi {
    private int id_Konsul;
    private String diagnosa;
    private Date tanggal;
    private int id_Dokter;
    private int id_Checkup;
    
    /*
    id_Konsul SERIAL PRIMARY KEY,
    diagnosa VARCHAR(100),
	tanggal DATE DEFAULT CURRENT_DATE,
    id_Dokter int REFERENCES Dokter(id_Dokter),
    id_Checkup int REFERENCES Checkup(id_Checkup)
     */
}
