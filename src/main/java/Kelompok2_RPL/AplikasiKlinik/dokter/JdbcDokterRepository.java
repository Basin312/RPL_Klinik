package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Kelompok2_RPL.AplikasiKlinik.pendaftaran.Pendaftaran;

@Repository
public class JdbcDokterRepository implements DokterRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pendaftaran> getAllPendaftaran(int id_Dokter){
        String sql = 
        """
        SELECT p.nama, pd.nomorAntrian, pd.is_Daftar
        FROM Pendaftaran pd
        JOIN Pasien p ON pd.id_Pasien = p.id_Pasien
        JOIN Jadwal j ON pd.id_jadwal = j.id_jadwal
        WHERE pd.tanggal_pendaftaran = CURRENT_DATE AND pd.is_Daftar = TRUE AND j.id_Dokter = ?
        """;
        
        List<Pendaftaran> list = jdbcTemplate.query(sql, this::MapRowToPendaftaran, id_Dokter);

        return list;
    }

    private Pendaftaran MapRowToPendaftaran(ResultSet resultSet, int rowNum) throws SQLException{
        boolean isDaftar = resultSet.getBoolean("is_Daftar");
        String status = isDaftar ? "Menunggu" : "Belum daftar ulang";

        return new Pendaftaran(
            resultSet.getInt("id_Pendaftaran"),
            resultSet.getDate("tanggal_pendaftaran"),
            resultSet.getInt("nomor_antrian"),
            resultSet.getInt("id_jadwal"),
            resultSet.getInt("id_pasien"),
            status
        );
    }
}
