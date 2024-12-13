package Kelompok2_RPL.AplikasiKlinik.checkup;

import java.util.List;
import java.util.Optional;

public interface CheckupRepository {
    Optional<Checkup> getCheckupByPasienAndCurrDate(int id_Pasien);
    List<Checkup> getCheckupByPasien(int id_Pasien);
}
