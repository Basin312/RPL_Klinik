package Kelompok2_RPL.AplikasiKlinik.User.Perawat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcPerawatRepository implements PerawatRepository{
    @Autowired
    private JdbcTemplate jdbcTemplates;

    public void save(Perawat user) throws Exception{
        String sql = "INSERT INTO Perawat (username, password) VALUES (?, ?)";
        jdbcTemplates.update(sql, user.getUsername(), user.getPassword());
    }
    public Optional<Perawat> FindPerawatUsername(String username) {
        String sql = "SELECT * FROM Perawat WHERE username = ?";
        List<Perawat> results = jdbcTemplates.query(sql, this::mapRowToUser, username);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    private Perawat mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new Perawat(
            resultSet.getString("username"),
            resultSet.getString("password")
        );
    }
}
