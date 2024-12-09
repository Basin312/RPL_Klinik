package Kelompok2_RPL.AplikasiKlinik.User.Pasien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcPasienRepository implements PasienRepository{
    @Autowired
    private JdbcTemplate jdbcTemplates;

    public void save(Pasien user) throws Exception{
        String sql = "INSERT INTO pasien (username, password) VALUES (?, ?)";
        jdbcTemplates.update(sql, user.getUsername(), user.getPassword());
    }
    public Optional<Pasien> FindUsername(String username) {
        String sql = "SELECT * FROM pasien WHERE username = ?";
        List<Pasien> results = jdbcTemplates.query(sql, this::mapRowToUser, username);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    private Pasien mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new Pasien(
            resultSet.getString("username"),
            resultSet.getString("password")
        );
    }
}