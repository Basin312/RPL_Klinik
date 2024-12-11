package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DaftarUlangService {
    
    @Autowired
    private JdbcDaftarUlangRepository daftarUlangRepository;

    public List<PasienStatusDTO> getAllPasienWithStatus() {
        return daftarUlangRepository.findAllWithStatus();
    }

    public List<PasienStatusDTO> searchPasienByName(String keyword) {
        return daftarUlangRepository.findByName(keyword);
    }

    public DetailPasienDTO getDetailPasienById(int id) {
        return daftarUlangRepository.findDetailPasienById(id);
    }

    public void updatePasienStatus(int idPasien, String status) {
        boolean is_daftar = status.equalsIgnoreCase("Selesai");
        daftarUlangRepository.updateIsDaftarByIdPasien(idPasien, is_daftar);
    }
}
