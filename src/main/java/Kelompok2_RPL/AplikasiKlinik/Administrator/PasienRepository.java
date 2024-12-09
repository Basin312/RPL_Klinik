package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.util.List;

public interface PasienRepository {
    List<Pasien> findAllWithStatus();
}
