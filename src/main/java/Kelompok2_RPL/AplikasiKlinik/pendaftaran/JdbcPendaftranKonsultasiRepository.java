package Kelompok2_RPL.AplikasiKlinik.pendaftaran;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcPendaftranKonsultasiRepository implements PendaftaranKonsultasiRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PendaftaranKonsultasi> getAllPendaftaran(int id_Dokter){
        String sql = 
        """
        SELECT *
        FROM Pendaftaran pd
        JOIN Pasien p ON pd.id_Pasien = p.id_Pasien
        JOIN Jadwal j ON pd.id_Jadwal = j.id_Jadwal
        WHERE pd.tanggal_pendaftaran = CURRENT_DATE AND pd.is_Checkup = TRUE AND j.id_Dokter = ?
        ORDER BY pd.nomor_antrian ASC
        """;
        
        List<PendaftaranKonsultasi> list = jdbcTemplate.query(sql, this::MapRowToPendaftaranKonsultasi, id_Dokter);
        return list;
    }

    public List<PendaftaranKonsultasi> searchPendaftaranByName(int id_Dokter, String filter){
        String sql = 
        """
        SELECT *
        FROM Pendaftaran pd
        JOIN Pasien p ON pd.id_Pasien = p.id_Pasien
        JOIN Jadwal j ON pd.id_Jadwal = j.id_Jadwal
        WHERE pd.tanggal_pendaftaran = CURRENT_DATE AND pd.is_Checkup = TRUE AND j.id_Dokter = ? AND p.nama ILIKE ?
        ORDER BY pd.nomor_antrian ASC
        """;

        String keyword = "%" + filter + "%";

        List<PendaftaranKonsultasi> list = jdbcTemplate.query(sql, this::MapRowToPendaftaranKonsultasi, id_Dokter, keyword);
        return list;
    }

    private PendaftaranKonsultasi MapRowToPendaftaranKonsultasi(ResultSet resultSet, int rowNum) throws SQLException{
        boolean isDaftar = resultSet.getBoolean("is_Daftar");
        String statusDaftar = isDaftar ? "Menunggu" : "Belum daftar ulang";

        boolean isCheckup = resultSet.getBoolean("is_Checkup");
        String statusCheckup = isCheckup ? "Menunggu" : "Belum checkup";

        return new PendaftaranKonsultasi(
            resultSet.getInt("id_Pendaftaran"),
            resultSet.getDate("tanggal_pendaftaran"),
            resultSet.getInt("nomor_antrian"),
            resultSet.getInt("id_Jadwal"),
            resultSet.getInt("id_Pasien"),
            statusDaftar,
            statusCheckup,
            statusCheckup,
            resultSet.getString("nama")
        );
    }
}
