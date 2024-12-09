package Kelompok2_RPL.AplikasiKlinik.User.Dokter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDokterRepository implements DokterRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void save(Dokter user) throws Exception{
        String sql = "INSERT INTO dokter (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }
    public Optional<Dokter> findByUsername(String username) {
        String sql = "SELECT * FROM dokter WHERE username = ?";
        List<Dokter> results = jdbcTemplate.query(sql, this::mapRowToUser, username);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    private Dokter mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new Dokter(
            resultSet.getString("username"),
            resultSet.getString("password")
        );
    }
}
