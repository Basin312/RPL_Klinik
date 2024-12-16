package Kelompok2_RPL.AplikasiKlinik.dokumen_pendukung;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DokumenPendukung {
    private int id_Dokumen;
    private String nama_dokumen;
    private String file_dokumen;
    private LocalDate tanggal;
    private int id_Pasien;
    private int id_Perawat;
}
