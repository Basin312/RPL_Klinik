package Kelompok2_RPL.AplikasiKlinik.dokumen_pendukung;

import java.util.List;
import java.util.Optional;

public interface DokumenPendukungRepository {
    List<DokumenPendukung> getDokumenPendukungByPasien(int id_Pasien);
    Optional<DokumenPendukung> getDokumenById(int id_Dokumen);
}
