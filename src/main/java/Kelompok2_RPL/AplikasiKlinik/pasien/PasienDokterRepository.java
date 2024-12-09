package Kelompok2_RPL.AplikasiKlinik.pasien;

import java.util.Optional;

public interface PasienDokterRepository {
    Optional<PasienDokter> getPasienDokterById(int id_Pasien);
}
