package Kelompok2_RPL.AplikasiKlinik;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcPasienRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SELECT_JOIN_QUERY = 
    """
        SELECT p.id_Pasien, p.nama, p.email, p.password, p.tanggal_lahir, p.jenis_kelamin, p.no_hp, p.alamat, pd.is_Daftar
        FROM Pasien p
        JOIN Pendaftaran pd ON p.id_Pasien = pd.id_Pasien
        WHERE pd.tanggal_pendaftaran = CURRENT_DATE
    """;

    public List<Pasien> findAllWithStatus() {
        return jdbcTemplate.query(SELECT_JOIN_QUERY, this::mapRowToPasien);
    }

    private Pasien mapRowToPasien(ResultSet resultSet, int rowNum) throws SQLException {
        boolean isDaftar = resultSet.getBoolean("is_Daftar");
        String status = isDaftar ? "Menunggu" : "Berhasil";
        
        return new Pasien(
            resultSet.getInt("id_Pasien"),
            resultSet.getString("nama"),
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getDate("tanggal_lahir"),
            resultSet.getString("jenis_kelamin"),
            resultSet.getString("no_hp"),
            resultSet.getString("alamat"),
            status
        );
    }
}
