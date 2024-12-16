package Kelompok2_RPL.AplikasiKlinik.pendaftaran;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
abstract class Pendaftaran {
    private int id_Pendaftaran;
    private Date tanggal_pendaftaran;
    private int nomor_antrian;
    private int id_Jadwal;
    private int id_Pasien;
    private String is_Daftar;
    private String is_Checkup;
    private String is_Konsul;

    /*
    id_Pendaftaran SERIAL PRIMARY KEY,
	tanggal_pendaftaran DATE DEFAULT CURRENT_DATE, --INI KALO is_Daftar == False (tanggal mendaftar online), kalo True jadi tanggal konsultasi ketemu dokter (current date)
	id_Jadwal int REFERENCES Jadwal(id_Jadwal), 
	id_Pasien int REFERENCES Pasien(id_Pasien), 
	is_Daftar BOOLEAN DEFAULT FALSE,--penanda udh dateng buat daftar ulang ga
    is_Konsul BOOLEAN DEFAULT FALSE, --penanda udh beres konsul belum, buat transaksi
	is_Checkup BOOLEAN DEFAULT FALSE,
	nomor_antrian int -- nomor antrian untuk setiap dokter pada tanggal tersebut (nomor antrian buat ketemu dokter)
     */
}
