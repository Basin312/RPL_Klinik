package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kelompok2_RPL.AplikasiKlinik.pendaftaran.PendaftaranKonsultasi;
import Kelompok2_RPL.AplikasiKlinik.pendaftaran.PendaftaranKonsultasiRepository;

@Service
public class DokterService {
    @Autowired
    private PendaftaranKonsultasiRepository pendaftaranKonsultasiRepository;

    public List<PendaftaranKonsultasi> getAllPendaftaran(int id_Dokter){
        return this.pendaftaranKonsultasiRepository.getAllPendaftaran(id_Dokter);
    }

    public List<PendaftaranKonsultasi> searchPendaftaranByName(int id_Dokter, String filter){
        return this.pendaftaranKonsultasiRepository.searchPendaftaranByName(id_Dokter, filter);
    }
}
