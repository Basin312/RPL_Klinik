package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.List;

public interface TransaksiRepository {
    List<PasienStatusDTO> findAllWithStatus();
    List<PasienStatusDTO> findByName(String keyword);
    DetailTransaksiDTO findDetailTransaksiById(int id);
    void updateTransaksi(int idPasien, boolean is_Bayar, String methodBayar);
} 