package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcDaftarUlangRepository implements DaftarUlangRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SELECT_PASIEN_STATUS = 
    """
        SELECT 
            p.id_Pasien, 
            p.nama, 
            CASE pd.is_Daftar 
                WHEN false THEN 'Menunggu' 
                ELSE 'Selesai' 
            END AS status
        FROM Pasien p
        JOIN Pendaftaran pd ON p.id_Pasien = pd.id_Pasien
        WHERE pd.tanggal_pendaftaran = CURRENT_DATE
    """;

    private final String SEARCH_PASIEN_BY_NAME =
    """
        SELECT 
            p.id_Pasien, 
            p.nama, 
            CASE pd.is_Daftar 
                WHEN false THEN 'Menunggu' 
                ELSE 'Selesai' 
            END AS status
        FROM Pasien p
        JOIN Pendaftaran pd ON p.id_Pasien = pd.id_Pasien
        WHERE pd.tanggal_pendaftaran = CURRENT_DATE
        AND (LOWER(p.nama) LIKE LOWER(CONCAT('%', ?, '%')) OR ? IS NULL)
    """;

    private final String SELECT_DETAIL_PASIEN =
    """
        SELECT 
            p.id_Pasien, 
            p.nama, 
            pd.tanggal_pendaftaran AS tanggalPendaftaran, 
            p.no_hp AS noHp, 
            p.jenis_kelamin AS jenisKelamin, 
            d.nama AS namaDokter, 
            j.jam AS jamKonsul,
            CASE pd.is_Daftar 
                WHEN false THEN 'Menunggu' 
                ELSE 'Selesai'
            END AS status,
            pd.nomor_antrian AS nomorAntrian,
            j.id_Dokter,
            pd.id_Jadwal
        FROM Pasien p
        JOIN Pendaftaran pd ON p.id_Pasien = pd.id_Pasien
        JOIN Jadwal j ON pd.id_Jadwal = j.id_Jadwal
        JOIN Dokter d ON j.id_Dokter = d.id_Dokter
        WHERE p.id_Pasien = ?
        ORDER BY pd.tanggal_pendaftaran DESC
        LIMIT 1;
    """;

    private final String UPDATE_ANTRIAN =
    """
        UPDATE Pendaftaran
        SET is_Daftar = TRUE,
            nomor_antrian = (
                SELECT COALESCE(MAX(p.nomor_antrian), 0) + 1
                FROM Pendaftaran p
                JOIN Jadwal j ON p.id_Jadwal = j.id_Jadwal
                WHERE p.tanggal_pendaftaran = CURRENT_DATE
                AND j.id_Dokter = (SELECT j2.id_Dokter 
                                    FROM Jadwal j2 
                                    WHERE j2.id_Jadwal = ?)
            )
        WHERE id_Pasien = ?
        AND id_Jadwal = ?
        AND is_Daftar = FALSE;
    """;

    @Override
    public List<PasienStatusDTO> findAllWithStatus() {
        return jdbcTemplate.query(SELECT_PASIEN_STATUS, this::mapRowToPasienStatusDTO);
    }

    @Override
    public List<PasienStatusDTO> findByName(String keyword) {
        return jdbcTemplate.query(SEARCH_PASIEN_BY_NAME, this::mapRowToPasienStatusDTO, keyword, keyword);
    }

    @Override
    public DetailPasienDTO findDetailPasienById(int id) {
        return jdbcTemplate.queryForObject(SELECT_DETAIL_PASIEN, this::mapRowToDetailPasienDTO, id);
    }

    @Override
    public void updateIsDaftarByIdPasien(int idPasien, boolean is_daftar) {
        jdbcTemplate.update(UPDATE_ANTRIAN, is_daftar, idPasien);
    }

    @Override
    public void updateDaftarUlangWithAntrian(int idPasien, int idJadwal) {
        jdbcTemplate.update(UPDATE_ANTRIAN, idJadwal, idPasien, idJadwal);
    }

    private PasienStatusDTO mapRowToPasienStatusDTO(ResultSet resultSet, int rowNum) throws SQLException {
        return new PasienStatusDTO(
            resultSet.getInt("id_Pasien"),
            resultSet.getString("nama"),
            resultSet.getString("status")
        );
    }

    private DetailPasienDTO mapRowToDetailPasienDTO(ResultSet resultSet, int rowNum) throws SQLException {
        String status = resultSet.getString("status");
        boolean isReadOnly = status != null && "Selesai".equalsIgnoreCase(status);
        return new DetailPasienDTO(
            resultSet.getInt("id_Pasien"),
            resultSet.getString("nama"),
            resultSet.getDate("tanggalPendaftaran"),
            resultSet.getString("noHp"),
            resultSet.getString("jenisKelamin"),
            resultSet.getString("namaDokter"),
            resultSet.getString("jamKonsul"),
            isReadOnly,
            resultSet.getInt("id_Dokter"),
            resultSet.getInt("id_Jadwal"),
            resultSet.getInt("nomorAntrian")
        );
    }
}