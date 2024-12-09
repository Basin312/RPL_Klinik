package Kelompok2_RPL.AplikasiKlinik.User.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcAdminRepository implements AdminRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void save(Admin user) throws Exception{
        String sql = "INSERT INTO Admin (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }
    public Optional<Admin> FindUsernameAdmin(String username) {
        String sql = "SELECT * FROM Admin WHERE username = ?";
        List<Admin> results = jdbcTemplate.query(sql, this::mapRowToUser, username);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    private Admin mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new Admin(
            resultSet.getString("username"),
            resultSet.getString("password")
        );
    }
}
