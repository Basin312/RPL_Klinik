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
        DetailPasienDTO detail = daftarUlangRepository.findDetailPasienById(idPasien);
    
        if (detail.isReadOnly()) {
            throw new UnsupportedOperationException("Daftar ulang ini sudah selesai dan tidak dapat diubah lagi.");
        }
    
        boolean is_daftar = status.equalsIgnoreCase("Selesai");
        if (is_daftar) {
            daftarUlangRepository.updateDaftarUlangWithAntrian(idPasien, detail.getIdJadwal()); // Pastikan ada `idJadwal` di DetailPasienDTO
        } else {
            daftarUlangRepository.updateIsDaftarByIdPasien(idPasien, false);
        }
    }
}
