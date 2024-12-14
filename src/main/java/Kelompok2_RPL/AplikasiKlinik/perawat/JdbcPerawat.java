package Kelompok2_RPL.AplikasiKlinik.perawat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Kelompok2_RPL.AplikasiKlinik.pasien.Pasien;

@Repository
public class JdbcPerawat implements PerawatRepository {

    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    public List<CheckUp> findAll() {
        String query = 
                    "SELECT pasien.id_pasien, nama, tanggal_pendaftaran " +
                    "FROM pasien " +
                    "JOIN ( " +
                    "    SELECT DISTINCT id_pasien, tanggal_pendaftaran " +
                    "    FROM pendaftaran " +
                    "    WHERE tanggal_pendaftaran = ? " + // Use parameterized query
                    "    AND is_daftar IS TRUE And is_checkup is false" +
                    ") AS data1 " +
                    "ON data1.id_pasien = pasien.id_pasien";
        return jdbc.query(query, this::mapRowtoCheckUp, LocalDate.now());
    
    }
    
    public CheckUp mapRowtoCheckUp(ResultSet resultSet, int rownum) throws SQLException{
        return new CheckUp(
        resultSet.getInt("id_pasien"),
        resultSet.getString("nama"),
        resultSet.getObject("tanggal_pendaftaran", LocalDate.class) != null ?
            resultSet.getObject("tanggal_pendaftaran", LocalDate.class) :
            resultSet.getDate("tanggal_pendaftaran").toLocalDate()
        );
    }

    @Override
    public Optional<Pasien> findById(int id) {
        String sql = "SELECT * FROM pasien WHERE id_pasien = ?";
        try {
            List<Pasien> result = jdbc.query(sql, this::mapRowToPasien, id);
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while fetching pasien with ID: " + id, e);
        }
    }

    public Pasien mapRowToPasien(ResultSet resultSet, int rownum) throws SQLException{
        return new Pasien(
            resultSet.getInt("id_pasien"), 
            resultSet.getString("nama"), 
            resultSet.getString("email"), 
            resultSet.getString("password"), 
            resultSet.getDate("tanggal_lahir"), 
            resultSet.getString("jenis_kelamin"), 
            resultSet.getString("no_hp"), 
            resultSet.getString("alamat"));
    }

    public void makeCheckup(double tinggi, double berat, String tekanan_darah, String tanggal, int id_Pasien) {
        LocalDate date = LocalDate.parse(tanggal);
        String sql = "INSERT INTO Checkup (tinggi, berat, tekanan_darah, tanggal, id_pasien) VALUES (?, ?, ?, ?, ?)";
        String sql2 = "UPDATE Pendaftaran SET is_checkup = true WHERE tanggal_pendaftaran = ? AND id_pasien = ?";
        try {
            // Insert the checkup data
            jdbc.update(sql, tinggi, berat, tekanan_darah, date, id_Pasien);
            // Update the Pendaftaran table
            jdbc.update(sql2, date, id_Pasien);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create checkup and update pendaftaran: " + e.getMessage());
        }
    }
    
}
