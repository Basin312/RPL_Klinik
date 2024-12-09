package Kelompok2_RPL.AplikasiKlinik.pendaftaran;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PendaftaranKonsultasi extends Pendaftaran {
    private String nama;

    public PendaftaranKonsultasi(int id_Pendaftaran, Date tanggal_pendaftaran, int nomor_antrian, int id_jadwal,
                                  int id_pasien, String is_Daftar, String is_Konsul, String nama) {
        super(id_Pendaftaran, tanggal_pendaftaran, nomor_antrian, id_jadwal, id_pasien, is_Daftar, is_Konsul);
        this.nama = nama;
    }
}