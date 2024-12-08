package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kelompok2_RPL.AplikasiKlinik.pendaftaran.Pendaftaran;

@Service
public class DokterService {
    @Autowired
    private DokterRepository dokterRepository;

    public List<Pendaftaran> getAllPendaftaran(int id_Dokter){
        return this.dokterRepository.getAllPendaftaran(id_Dokter);
    }
}
