package Kelompok2_RPL.AplikasiKlinik;

import java.util.List;

public interface PasienRepository {
    List<Pasien> findAllWithStatus();
}
