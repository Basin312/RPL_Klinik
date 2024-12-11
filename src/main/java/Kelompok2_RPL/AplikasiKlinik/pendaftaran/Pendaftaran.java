package Kelompok2_RPL.AplikasiKlinik.pendaftaran;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
abstract class Pendaftaran {
    private int id_Pendaftaran;
    private Date tanggal_pendaftaran;
    private int nomorAntrian;
    private int id_jadwal;
    private int id_pasien;
    private String is_Daftar;
    private String is_Konsul;

    // id_Pendaftaran int PRIMARY KEY,
	// tanggal_pendaftaran TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --bukan tanggal konsultasi
	// id_jadwal int references jadwal(id_jadwal), 
	// id_pasien int references pasien(id_pasien), 
	// is_Daftar BOOLEAN DEFAULT FALSE,--penanda dateng buat daftar ulang ga
	// nomorAntrian int -- nomor antrian untuk setiap dokter pada tanggal tersebut (nomor antrian buat ketemu dokter)
}
