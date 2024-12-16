package Kelompok2_RPL.AplikasiKlinik.home;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.xml.transform.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcHomeRepository implements HomeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Home> listPendaftaran(int i) {
        String query = 
        "SELECT " +
        "    * " +
        "FROM " +
        "    hari " +
        "JOIN ( " +
        "    SELECT " +
        "        id_pasien, nama, id_hari, nomor_antrian, is_checkup, is_daftar " +
        "    FROM " +
        "        dokter " +
        "    JOIN ( " +
        "        SELECT " +
        "            id_pasien, id_hari, id_dokter, nomor_antrian, is_daftar, is_checkup " +
        "        FROM " +
        "            pendaftaran " +
        "        JOIN " +
        "            jadwal " +
        "        ON " +
        "            jadwal.id_jadwal = pendaftaran.id_jadwal " +
        "    ) AS data1 " +
        "    ON " +
        "        dokter.id_dokter = data1.id_dokter " +
        ") AS data2 " +
        "ON " +
        "    data2.id_hari = hari.id_hari " +
        "WHERE " +
        "    id_pasien = ?";
        return jdbcTemplate.query(query, this::mapToRowHome, i);
    }

    public Home mapToRowHome(ResultSet resultSet, int rownum)throws SQLException{
        return new Home(resultSet.getInt("id_pasien"),
                        resultSet.getString("hari"),
                        resultSet.getString("nama"),
                        resultSet.getInt("nomor_antrian"),
                        resultSet.getBoolean("is_checkup"),
                        resultSet.getBoolean("is_daftar"));
    }
    // public Home findNomorAntrian(int idPasien) {
    //     String query = "SELECT nomor_antrian FROM pendaftaran WHERE id_pasien = ?";
    //     try {
    //         int nomorAntrian = jdbcTemplate.queryForObject(query, Integer.class, idPasien);
    //         return new Home(nomorAntrian);
    //     } catch (EmptyResultDataAccessException e) {
    //         return null; 
    //     }
    // }
}

