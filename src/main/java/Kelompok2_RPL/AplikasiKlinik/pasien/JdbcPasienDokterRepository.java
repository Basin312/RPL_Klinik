package Kelompok2_RPL.AplikasiKlinik.pasien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcPasienDokterRepository implements PasienDokterRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<PasienDokter> getPasienDokterById(int id_Pasien){
        String sql =
        """
        SELECT *
        FROM Pasien p
        WHERE id_Pasien = ?
        """;

        List<PasienDokter> pasien = jdbcTemplate.query(sql, this::mapRowToPasienDokter, id_Pasien);
        return pasien.size() == 0 ? Optional.empty() : Optional.of(pasien.get(0));
    }

    private PasienDokter mapRowToPasienDokter(ResultSet resultSet, int rowNum) throws SQLException{
        LocalDate tanggal_lahir = resultSet.getObject("tanggal_lahir", LocalDate.class);
        LocalDate today = LocalDate.now(); //dapetin tanggal hari ini
        Period period = Period.between(tanggal_lahir, today); //menghitung periode umur
        int umur = period.getYears(); //ambil tahun nya


        return new PasienDokter(
            resultSet.getString("p.nama"),
            umur,
            resultSet.getString("p.jenis_kelamin"),
            tanggal_lahir
        );
    }
}
