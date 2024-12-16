package Kelompok2_RPL.AplikasiKlinik.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcHomeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Home findNomorAntrian(int idPasien) {
        String query = "SELECT nomor_antrian FROM pendaftaran WHERE id_pasien = ?";
        try {
            int nomorAntrian = jdbcTemplate.queryForObject(query, Integer.class, idPasien);
            return new Home(nomorAntrian);
        } catch (EmptyResultDataAccessException e) {
            return null; 
        }
    }
}

