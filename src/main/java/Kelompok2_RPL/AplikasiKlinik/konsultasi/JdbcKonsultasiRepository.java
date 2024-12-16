package Kelompok2_RPL.AplikasiKlinik.konsultasi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcKonsultasiRepository implements KonsultasiRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void tambahKonsultasi(String diagnosa, int id_Dokter, int id_Checkup) {
        String sql =
        """
        INSERT INTO Konsultasi (diagnosa, id_Dokter, id_Checkup) VALUES (?,?,?)
        """;
        jdbcTemplate.update(sql, diagnosa, id_Dokter, id_Checkup);
    }

    public Optional<Konsultasi> getKonsultasiTerbaruByDokterAndCurrDate(int id_Dokter){
        String sql = 
        """
        SELECT *
        FROM Konsultasi
        WHERE tanggal = CURRENT_DATE AND id_Dokter = ?
        ORDER BY id_Konsul DESC
        LIMIT 1
        """;

        List<Konsultasi> konsultasi = jdbcTemplate.query(sql, this::mapRowToKonsultasi, id_Dokter);
        return konsultasi.size() == 0 ? Optional.empty() : Optional.of(konsultasi.get(0));
    }

    public List<Konsultasi> getKonsultasiByPasien(int id_Pasien){
        String sql = 
        """
        SELECT k.id_Konsul, k.diagnosa, k.tanggal, k.id_Dokter, k.id_Checkup
        FROM Konsultasi k
        JOIN Checkup c ON k.id_Checkup = c.id_Checkup
        WHERE c.id_Pasien = ?
        ORDER BY k.tanggal DESC
        """;

        List<Konsultasi> list = jdbcTemplate.query(sql, this::mapRowToKonsultasi, id_Pasien);
        return list;
    }

    private Konsultasi mapRowToKonsultasi(ResultSet resultSet, int rowNum) throws SQLException{
        return new Konsultasi(
            resultSet.getObject("id_Konsul", Integer.class), 
            resultSet.getString("diagnosa"), 
            resultSet.getObject("tanggal", LocalDate.class), 
            resultSet.getObject("id_Dokter", Integer.class), 
            resultSet.getObject("id_Checkup", Integer.class)
        );
    }
}
