package Kelompok2_RPL.AplikasiKlinik.dokumen_pendukung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDokumenPendukungRepository implements DokumenPendukungRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DokumenPendukung> getDokumenPendukungByPasien(int id_Pasien){
        String sql =
        """
        SELECT *
        FROM DokumenPendukung
        WHERE id_Pasien = ?
        ORDER BY tanggal DESC
        """;

        List<DokumenPendukung> list = jdbcTemplate.query(sql, this::mapRowToDokumenPendukung, id_Pasien);
        return list;
    }

    public Optional<DokumenPendukung> getDokumenById(int id_Dokumen){
        String sql = 
        """
        SELECT *
        FROM DokumenPendukung
        WHERE id_Dokumen = ?
        """;

        List<DokumenPendukung> dokumen = jdbcTemplate.query(sql, this::mapRowToDokumenPendukung, id_Dokumen);
        return dokumen.size() == 0 ? Optional.empty() : Optional.of(dokumen.get(0));
    }

    private DokumenPendukung mapRowToDokumenPendukung(ResultSet resultSet, int rowNum) throws SQLException{
        return new DokumenPendukung(
            resultSet.getInt("id_Dokumen"), 
            resultSet.getString("nama_dokumen"), 
            resultSet.getString("file_dokumen"), 
            resultSet.getObject("tanggal", LocalDate.class), 
            resultSet.getInt("id_Pasien"), 
            resultSet.getInt("id_Perawat")
        );
    }
}
