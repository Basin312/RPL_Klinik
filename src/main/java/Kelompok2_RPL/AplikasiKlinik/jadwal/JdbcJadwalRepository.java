package Kelompok2_RPL.AplikasiKlinik.jadwal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcJadwalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Jadwal> findAll() {
      
        String sqlQuery = 
                            "SELECT * " +
                            "FROM hari " +
                            "JOIN ( " +
                            "    SELECT id_jadwal, jam, id_hari, id_dokter, nama, status, specialis.id_specialis, limit_pasien, nama_specialis, harga " +
                            "    FROM specialis " +
                            "    JOIN ( " +
                            "        SELECT jam, id_jadwal, jadwal.id_dokter, id_hari, limit_pasien, nama, status, id_specialis " +
                            "        FROM jadwal " +
                            "        JOIN dokter ON dokter.id_dokter = jadwal.id_dokter " +
                            "    ) AS data1 ON data1.id_specialis = specialis.id_specialis " +
                            ") AS data2 ON data2.id_hari = hari.id_hari";

        return jdbcTemplate.query(sqlQuery, this::mapToRowJadwal);
    }

    public Jadwal mapToRowJadwal(ResultSet resultSet, int rowNumber) throws SQLException{
        return new Jadwal(
            resultSet.getString("nama"),
            resultSet.getString("nama_specialis"),
            resultSet.getInt("id_hari"),
            resultSet.getString("jam"),
            resultSet.getInt("limit_pasien")
        );
    }

    public List<Dokter> findAllDokter() {
        String sqlQuery = "select *"+
                          "from dokter";
        return jdbcTemplate.query(sqlQuery, this::mapToRowDokter);
    }

    public List<Specialis> findAllSpecialis() {
        String sqlQuery = 
                        "Select *"+
                        "from specialis";
        return jdbcTemplate.query(sqlQuery, this::mapToRowSpecialis);
    }

    public Specialis mapToRowSpecialis(ResultSet resultSet, int rowNumber) throws SQLException{
        return new Specialis(
            resultSet.getInt("id_specialis"),
            resultSet.getString("nama_specialis")
        );
    }

    public Dokter mapToRowDokter(ResultSet resultSet, int rowNumber) throws SQLException{
        return new Dokter(
            resultSet.getInt("id_dokter"),
            resultSet.getString("nama")
        );
    }

    public List<Jadwal> findBySpecialisId(int idSpecialis) {
    String sqlQuery = 
        "SELECT * " +
        "FROM hari " +
        "JOIN ( " +
        "    SELECT id_jadwal, jam, id_hari, id_dokter, nama, status, specialis.id_specialis, limit_pasien, nama_specialis, harga " +
        "    FROM specialis " +
        "    JOIN ( " +
        "        SELECT jam, id_jadwal, jadwal.id_dokter, id_hari, limit_pasien, nama, status, id_specialis " +
        "        FROM jadwal " +
        "        JOIN dokter ON dokter.id_dokter = jadwal.id_dokter " +
        "    ) AS data1 ON data1.id_specialis = specialis.id_specialis " +
        ") AS data2 ON data2.id_hari = hari.id_hari " +
        "WHERE id_specialis = ?";
    
    return jdbcTemplate.query(sqlQuery, this::mapToRowJadwal, idSpecialis);
    }

    public List<Jadwal> findByDokterId(int idDokter) {
    String sqlQuery = 
        "SELECT * " +
        "FROM hari " +
        "JOIN ( " +
        "    SELECT id_jadwal, jam, id_hari, id_dokter, nama, status, specialis.id_specialis, limit_pasien, nama_specialis, harga " +
        "    FROM specialis " +
        "    JOIN ( " +
        "        SELECT jam, id_jadwal, jadwal.id_dokter, id_hari, limit_pasien, nama, status, id_specialis " +
        "        FROM jadwal " +
        "        JOIN dokter ON dokter.id_dokter = jadwal.id_dokter " +
        "    ) AS data1 ON data1.id_specialis = specialis.id_specialis " +
        ") AS data2 ON data2.id_hari = hari.id_hari " +
        "WHERE id_dokter = ?";
    
    return jdbcTemplate.query(sqlQuery, this::mapToRowJadwal, idDokter);
    }
}
