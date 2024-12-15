package Kelompok2_RPL.AplikasiKlinik.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcHomeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Home findNomorAntrian(int idPasien) {
        String query = "SELECT nomorantrian FROM pendaftaran WHERE id_pasien = ? AND is_daftar = true";
        return jdbcTemplate.queryForObject(query, new Object[]{idPasien}, 
            BeanPropertyRowMapper.newInstance(Home.class)); //karena setiap nomor antian dapetnya sesudah daftar ulang
    }
}

