package Kelompok2_RPL.AplikasiKlinik.Administrator;

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
}
