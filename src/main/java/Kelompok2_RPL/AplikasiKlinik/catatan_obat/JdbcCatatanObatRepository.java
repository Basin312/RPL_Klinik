package Kelompok2_RPL.AplikasiKlinik.catatan_obat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCatatanObatRepository implements CatatanObatRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void tambahObat(String obat, String dosis, int id_Konsul) {
        String sql =
        """
        INSERT INTO CatatanObat (obat, dosis, id_Konsul) VALUES (?,?,?)
        """;
        jdbcTemplate.update(sql, obat, dosis, id_Konsul);
    }

    public List<CatatanObat> getCatatanObatByPasien(int id_Pasien){
        String sql =
        """
        SELECT co.id_Obat, co.obat, co.dosis, co.tanggal, co.id_Konsul
        FROM CatatanObat co
        JOIN Konsultasi k ON co.id_Konsul = k.id_Konsul
        JOIN Checkup c ON k.id_Checkup = c.id_Checkup 
        WHERE c.id_Pasien = ?
        ORDER BY co.tanggal DESC
        """;

        List<CatatanObat> list = this.jdbcTemplate.query(sql, this::mapRowToCatatanObat, id_Pasien);
        return list;
    }

    private CatatanObat mapRowToCatatanObat(ResultSet resultSet, int rowNum) throws SQLException{
        return new CatatanObat(
            resultSet.getObject("id_Obat", Integer.class), 
            resultSet.getString("obat"), 
            resultSet.getString("dosis"), 
            resultSet.getObject("tanggal", LocalDate.class), 
            resultSet.getObject("id_Konsul", Integer.class)
        );
    }
}
