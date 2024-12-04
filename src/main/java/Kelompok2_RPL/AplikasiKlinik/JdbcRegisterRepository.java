package Kelompok2_RPL.AplikasiKlinik;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcRegisterRepository implements RegisterRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void save(Pasien pasien) throws Exception {
        String sql = "INSERT INTO pasien (nama, email, password, )"
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Pasien> findByUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUser'");
    }
    
}
