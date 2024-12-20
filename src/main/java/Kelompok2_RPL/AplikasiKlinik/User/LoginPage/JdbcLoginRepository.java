package Kelompok2_RPL.AplikasiKlinik.User.LoginPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcLoginRepository implements LoginRepository{
    @Autowired
    private JdbcTemplate jdbcTemplates;
    
    public Optional<Login> findPerawat(String email) {
        String sql = "SELECT * FROM Perawat WHERE email = ?";
        List<Login> results = jdbcTemplates.query(sql, this::mapRowToPerawat, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    public Optional<Login> findPasien(String email) {
        String sql = "SELECT * FROM Pasien WHERE email = ?";
        List<Login> results = jdbcTemplates.query(sql, this::mapRowToPasien, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    public Optional<Login> findDokter(String email) {
        String sql = "SELECT * FROM Dokter WHERE email = ?";
        List<Login> results = jdbcTemplates.query(sql, this::mapRowToDokter, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    public Optional<Login> findAdmin(String email) {
        String sql = "SELECT * FROM SistemAdmin WHERE email = ?";
        List<Login> results = jdbcTemplates.query(sql, this::mapRowToAdmin, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<Login> findAdministrator(String email) {
        String sql = "SELECT * FROM Administrator WHERE email = ?";
        List<Login> results = jdbcTemplates.query(sql, this::mapRowToAdministrator, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    private Login mapRowToPasien(ResultSet resultSet, int rowNum) throws SQLException {
        return new Login(
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getInt("id_pasien")
        );
    }
    private Login mapRowToDokter(ResultSet resultSet, int rowNum) throws SQLException {
        return new Login(
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getInt("id_dokter")
        );
    }
    private Login mapRowToPerawat(ResultSet resultSet, int rowNum) throws SQLException {
        return new Login(
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getInt("id_perawat")
        );
    }
    private Login mapRowToAdministrator(ResultSet resultSet, int rowNum) throws SQLException {
        return new Login(
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getInt("id_administrator")
        );
    }
    private Login mapRowToAdmin(ResultSet resultSet, int rowNum) throws SQLException {
        return new Login(
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getInt("id_sistemadmin")
        );
    }
}