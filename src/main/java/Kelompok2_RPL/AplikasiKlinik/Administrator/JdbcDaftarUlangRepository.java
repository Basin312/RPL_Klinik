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

    private final String SELECT_DETAIL_PASIEN =
    """
        SELECT p.nama, pd.tanggal_pendaftaran AS tanggalPendaftaran, p.no_hp AS noHp, p.jenis_kelamin AS jenisKelamin, d.nama AS namaDokter, j.jam AS jamKonsul
        FROM Pasien p
        JOIN Pendaftaran pd ON p.id_Pasien = pd.id_Pasien
        JOIN Jadwal j ON pd.id_Jadwal = j.id_Jadwal
        JOIN Dokter d ON j.id_Dokter = d.id_Dokter
        WHERE p.id_Pasien = ?
    """;

    @Override
    public List<PasienStatusDTO> findAllWithStatus() {
        return jdbcTemplate.query(SELECT_PASIEN_STATUS, this::mapRowToPasienStatusDTO);
    }

    @Override
    public DetailPasienDTO findDetailPasienById(int id) {
        return jdbcTemplate.queryForObject(SELECT_DETAIL_PASIEN, this::mapRowToDetailPasienDTO, id);
    }

    private PasienStatusDTO mapRowToPasienStatusDTO(ResultSet resultSet, int rowNum) throws SQLException {
        return new PasienStatusDTO(
            resultSet.getInt("id_Pasien"),
            resultSet.getString("nama"),
            resultSet.getString("status")
        );
    }

    private DetailPasienDTO mapRowToDetailPasienDTO(ResultSet resultSet, int rowNum) throws SQLException {
        return new DetailPasienDTO(
            resultSet.getString("nama"),
            resultSet.getDate("tanggalPendaftaran"),
            resultSet.getString("noHp"),
            resultSet.getString("jenisKelamin"),
            resultSet.getString("namaDokter"),
            resultSet.getString("jamKonsul")
        );
    }
}
