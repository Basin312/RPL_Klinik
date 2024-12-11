package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kelompok2_RPL.AplikasiKlinik.pendaftaran.PendaftaranKonsultasi;

@Service
public class DokterService {
    @Autowired
    private DokterRepository dokterRepository;

    public List<PendaftaranKonsultasi> getAllPendaftaran(int id_Dokter){
        return this.dokterRepository.getAllPendaftaran(id_Dokter);
    }

    public List<PendaftaranKonsultasi> searchPendaftaranByName(int id_Dokter, String filter){
        return this.dokterRepository.searchPendaftaranByName(id_Dokter, filter);
    }
}
