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
        AND pd.is_Daftar = true
        AND pd.is_Checkup = true
        AND pd.is_Konsul = true
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
        AND pd.is_Daftar = true
        AND pd.is_Checkup = true
        AND pd.is_Konsul = true
        AND (LOWER(p.nama) LIKE LOWER(CONCAT('%', ?, '%')) OR ? IS NULL)
    """;

    private final String SELECT_DETAIL_TRANSAKSI_PASIEN =
    """
        SELECT 
            p.id_Pasien, 
            p.nama, 
            pd.tanggal_pendaftaran AS tanggalKonsultasi, 
            s.harga AS harga, 
            t.methodBayar AS methodBayar,
            CASE t.is_Bayar
                WHEN false THEN 'Belum Bayar' 
                ELSE 'Sudah Bayar' 
            END AS status
        FROM Pasien p
        JOIN Pendaftaran pd ON p.id_Pasien = pd.id_Pasien
        JOIN Transaksi t ON pd.id_Pendaftaran = t.id_Pendaftaran
        JOIN Jadwal j ON pd.id_Jadwal = j.id_Jadwal
        JOIN Dokter d ON j.id_Dokter = d.id_Dokter
        JOIN Specialis s ON d.id_Specialis = s.id_Specialis
        WHERE p.id_Pasien = ?
    """;

    private final String SELECT_ID_PENDAFTARAN_BY_PASIEN =
    """
        SELECT pd.id_Pendaftaran
        FROM Pendaftaran pd
        JOIN Pasien p ON pd.id_Pasien = p.id_Pasien
        WHERE p.id_Pasien = ?
    """;

    private final String UPDATE_TRANSAKSI = 
    """
        UPDATE Transaksi 
        SET is_Bayar = ?, methodBayar = ?::method_bayar_enum
        WHERE id_Pendaftaran = ?
    """;

    @Override
    public List<PasienStatusDTO> findAllWithStatus() {
        return jdbcTemplate.query(SELECT_PASIEN_TRANSAKSI, this::mapRowToPasienStatusDTO);
    }
    
    @Override
    public List<PasienStatusDTO> findByName(String keyword) {
        return jdbcTemplate.query(TRANSAKSI_SEARCH_PASIEN_BY_NAME, this::mapRowToPasienStatusDTO, keyword, keyword);
    }

    @Override
    public DetailTransaksiDTO findDetailTransaksiById(int id) {
        return jdbcTemplate.queryForObject(SELECT_DETAIL_TRANSAKSI_PASIEN, this::mapRowToDetailTransaksiDTO, id);
    }

    @Override
    public void updateTransaksi(int idPasien, boolean is_Bayar, String methodPembayaran) {
        Integer idPendaftaran = jdbcTemplate.queryForObject(SELECT_ID_PENDAFTARAN_BY_PASIEN, Integer.class, idPasien);
        jdbcTemplate.update(UPDATE_TRANSAKSI, is_Bayar, methodPembayaran, idPendaftaran);
    }

    private PasienStatusDTO mapRowToPasienStatusDTO(ResultSet resultSet, int rowNum) throws SQLException {
        return new PasienStatusDTO(
            resultSet.getInt("id_Pasien"),
            resultSet.getString("nama"),
            resultSet.getString("status")
        );
    }

    private DetailTransaksiDTO mapRowToDetailTransaksiDTO(ResultSet resultSet, int rowNum) throws SQLException {
        boolean isReadOnly = "Sudah Bayar".equalsIgnoreCase(resultSet.getString("status"));
        return new DetailTransaksiDTO(
            resultSet.getInt("id_Pasien"),
            resultSet.getString("nama"),
            resultSet.getDate("tanggalKonsultasi"),
            resultSet.getBigDecimal("harga"),
            DetailTransaksiDTO.MethodBayar.valueOf(resultSet.getString("methodBayar")),
            isReadOnly
        );
    }
}