package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaksiService {
    
    @Autowired
    private JdbcTransaksiRepository TransaksiRepository;

    public List<PasienStatusDTO> getAllPasienWithStatus() {
        return TransaksiRepository.findAllWithStatus();
    }

    public List<PasienStatusDTO> searchPasienByName(String keyword) {
        return TransaksiRepository.findByName(keyword);
    }

    public DetailTransaksiDTO getDetailTransaksi(int idPasien) {
        return TransaksiRepository.findDetailTransaksiById(idPasien);
    }

    public void updateTransaksiByPasien(int idPasien, String status, String methodBayar) {
        DetailTransaksiDTO detail = TransaksiRepository.findDetailTransaksiById(idPasien);

        if (detail.isReadOnly()) {
            throw new UnsupportedOperationException("Transaksi ini sudah selesai dan tidak dapat diubah lagi.");
        }
        
        boolean is_Bayar = status.equalsIgnoreCase("Sudah Bayar");
        TransaksiRepository.updateTransaksi(idPasien, is_Bayar, methodBayar);
    }
}