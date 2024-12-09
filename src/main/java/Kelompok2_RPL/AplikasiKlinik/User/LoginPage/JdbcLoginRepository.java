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
        List<Login> results = jdbcTemplates.query(sql, this::mapRowToUser, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    public Optional<Login> findPasien(String email) {
        String sql = "SELECT * FROM Pasien WHERE email = ?";
        List<Login> results = jdbcTemplates.query(sql, this::mapRowToUser, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    public Optional<Login> findDokter(String email) {
        String sql = "SELECT * FROM Dokter WHERE email = ?";
        List<Login> results = jdbcTemplates.query(sql, this::mapRowToUser, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    public Optional<Login> findAdmin(String email) {
        String sql = "SELECT * FROM Admin WHERE email = ?";
        List<Login> results = jdbcTemplates.query(sql, this::mapRowToUser, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<Login> findAdministrator(String email) {
        String sql = "SELECT * FROM Administrator WHERE email = ?";
        List<Login> results = jdbcTemplates.query(sql, this::mapRowToUser, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    private Login mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new Login(
            resultSet.getString("email"),
            resultSet.getString("password")
        );
    }
}