package Kelompok2_RPL.AplikasiKlinik.jadwal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JadwalService {

    @Autowired
    private JdbcJadwalRepository jadwalRepository;
 
    public List<Jadwal> getAllJadwal() {
        return jadwalRepository.findAll();
    }
 
    public List<Dokter> getAllDokter() {
        return jadwalRepository.findAllDokter();
    }
 
    public List<Specialis> getAllSpecialis() {
        return jadwalRepository.findAllSpecialis();
    }
}
