package Kelompok2_RPL.AplikasiKlinik.checkup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCheckupRepository implements CheckupRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //ambil checkup berdsarkan id_Pasien dan tanggal sekarang
    public Optional<Checkup> getCheckupByPasienAndCurrDate(int id_Pasien){
        String sql =
        """
        SELECT *
        FROM Checkup c
        WHERE id_Pasien = ? AND tanggal = CURRENT_DATE
        """;

        List<Checkup> checkup = jdbcTemplate.query(sql, this::mapRowToCheckup, id_Pasien);
        return checkup.size() == 0 ? Optional.empty() : Optional.of(checkup.get(0));
    }

    private Checkup mapRowToCheckup(ResultSet resultSet, int rowNum) throws SQLException{
        return new Checkup(
            resultSet.getInt("id_Checkup"),
            resultSet.getDouble("tinggi"),
            resultSet.getDouble("berat"),
            resultSet.getString("tekanan_darah"),
            resultSet.getObject("tanggal", LocalDate.class),
            resultSet.getInt("id_Pasien")
        );
    }
}
