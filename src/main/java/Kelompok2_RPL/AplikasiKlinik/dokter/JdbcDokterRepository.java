package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Kelompok2_RPL.AplikasiKlinik.pendaftaran.PendaftaranKonsultasi;

@Repository
public class JdbcDokterRepository implements DokterRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PendaftaranKonsultasi> getAllPendaftaran(int id_Dokter){
        String sql = 
        """
        SELECT *
        FROM Pendaftaran pd
        JOIN Pasien p ON pd.id_Pasien = p.id_Pasien
        JOIN Jadwal j ON pd.id_jadwal = j.id_jadwal
        WHERE pd.tanggal_pendaftaran = CURRENT_DATE AND pd.is_Daftar = TRUE AND j.id_Dokter = ?
        """;
        
        List<PendaftaranKonsultasi> list = jdbcTemplate.query(sql, this::MapRowToPendaftaranKonsultasi, id_Dokter);

        return list;
    }

    private PendaftaranKonsultasi MapRowToPendaftaranKonsultasi(ResultSet resultSet, int rowNum) throws SQLException{
        boolean isDaftar = resultSet.getBoolean("is_Daftar");
        String statusDaftar = isDaftar ? "Menunggu" : "Belum daftar ulang";


        return new PendaftaranKonsultasi(
            resultSet.getInt("id_Pendaftaran"),
            resultSet.getDate("tanggal_pendaftaran"),
            resultSet.getInt("nomor_antrian"),
            resultSet.getInt("id_Jadwal"),
            resultSet.getInt("id_Pasien"),
            statusDaftar,
            statusDaftar,
            resultSet.getString("nama")
        );
    }
}
