package Kelompok2_RPL.AplikasiKlinik.User.Administrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcAdministratorRepository implements AdministratorRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void save(Administrator user) throws Exception{
        String sql = "INSERT INTO Administrator (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }
    public Optional<Administrator> FindUsernameAdministrator(String username) {
        String sql = "SELECT * FROM Administrator WHERE username = ?";
        List<Administrator> results = jdbcTemplate.query(sql, this::mapRowToUser, username);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }
    private Administrator mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new Administrator(
            resultSet.getString("username"),
            resultSet.getString("password")
        );
    }
}
