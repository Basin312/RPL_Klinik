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
                            "    SELECT id_jadwal, jam, id_hari, id_dokter, nama, status, specialis.id_specialis, limitpasien, nama_specialis, harga " +
                            "    FROM specialis " +
                            "    JOIN ( " +
                            "        SELECT jam, id_jadwal, jadwal.id_dokter, id_hari, limitpasien, nama, status, id_specialis " +
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
            resultSet.getString("hari"),
            resultSet.getInt("limitpasien")
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
}
