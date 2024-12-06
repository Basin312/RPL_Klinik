package Kelompok2_RPL.AplikasiKlinik.register;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Kelompok2_RPL.AplikasiKlinik.Pasien;

@Repository
public class JdbcRegisterRepository implements RegisterRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void save(Pasien pasien) throws Exception {
        String sql = "INSERT INTO pasien (nama, email, password, tanggal_lahir, jenis_kelamin, no_hp, alamat) VALUES (?,?,?,?,?,?,?)";
        jdbc.update(sql, pasien.getNama(), pasien.getEmail(), pasien.getPassword(), pasien.getTanggalLahir().toString(), pasien.getJenis(), pasien.getNoHp(), pasien.getAlamat());
    }

    public Pasien mapToPasien(ResultSet resultSet, int rowNum) throws SQLException{
        return new Pasien(
            resultSet.getString("nama"),
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getString("password"),
            resultSet.getObject("tanggal_lahir", LocalDate.class),
            resultSet.getString("no_hp"),
            resultSet.getString("alamat"),
            resultSet.getString("jenis_kelamin")
            );    
    }

    @Override
    public Optional<Pasien> findByEmail(String email) {
        String sql ="SELECT * FROM pasien WHERE email = ? ";
        List<Pasien> result = jdbc.query(sql, this::mapToPasien, email);
        return result.size() == 0 ? Optional.empty() : Optional.of(result.get(0));
    }
    
}
