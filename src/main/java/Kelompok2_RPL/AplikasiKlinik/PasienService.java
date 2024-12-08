package Kelompok2_RPL.AplikasiKlinik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasienService {
    
    @Autowired
    private JdbcPasienRepository pasienRepository;

    public List<Pasien> getAllPasienWithStatus() {
        return pasienRepository.findAllWithStatus();
    }

    public Pasien getPasienById(int id) {
        return pasienRepository.findById(id);
    }

    public void updatePasienStatus(int id, boolean isDaftar) {
        pasienRepository.updateStatus(id, isDaftar);
    }
}
