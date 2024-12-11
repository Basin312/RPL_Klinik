package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTransaksiRepository implements TransaksiRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SELECT_PASIEN_TRANSAKSI = 
    """
        SELECT 
            p.id_Pasien, 
            p.nama, 
            CASE t.is_Bayar
                WHEN false THEN 'Belum Bayar' 
                ELSE 'Sudah Bayar' 
            END AS status
        FROM Pasien p
        JOIN Pendaftaran pd ON p.id_Pasien = pd.id_Pasien
        JOIN Transaksi t ON pd.id_Pendaftaran = t.id_Pendaftaran
        WHERE pd.tanggal_pendaftaran = CURRENT_DATE
    """;

    private final String TRANSAKSI_SEARCH_PASIEN_BY_NAME =
    """
        SELECT 
            p.id_Pasien, 
            p.nama, 
            CASE t.is_Bayar 
                WHEN false THEN 'Belum Bayar' 
                ELSE 'Sudah Bayar' 
            END AS status
        FROM Pasien p
        JOIN Pendaftaran pd ON p.id_Pasien = pd.id_Pasien
        JOIN Transaksi t ON pd.id_Pendaftaran = t.id_Pendaftaran
        WHERE pd.tanggal_pendaftaran = CURRENT_DATE
        AND (LOWER(p.nama) LIKE LOWER(CONCAT('%', ?, '%')) OR ? IS NULL)
    """;

    @Override
    public List<PasienStatusDTO> findAllWithStatus() {
        return jdbcTemplate.query(SELECT_PASIEN_TRANSAKSI, this::mapRowToPasienStatusDTO);
    }
    
    @Override
    public List<PasienStatusDTO> findByName(String keyword) {
        return jdbcTemplate.query(TRANSAKSI_SEARCH_PASIEN_BY_NAME, this::mapRowToPasienStatusDTO, keyword, keyword);
    }

    private PasienStatusDTO mapRowToPasienStatusDTO(ResultSet resultSet, int rowNum) throws SQLException {
        return new PasienStatusDTO(
            resultSet.getInt("id_Pasien"),
            resultSet.getString("nama"),
            resultSet.getString("status")
        );
    }

    
}
