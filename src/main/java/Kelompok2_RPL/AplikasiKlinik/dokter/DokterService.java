package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kelompok2_RPL.AplikasiKlinik.checkup.Checkup;
import Kelompok2_RPL.AplikasiKlinik.checkup.CheckupRepository;
import Kelompok2_RPL.AplikasiKlinik.pasien.PasienDokter;
import Kelompok2_RPL.AplikasiKlinik.pasien.PasienDokterRepository;
import Kelompok2_RPL.AplikasiKlinik.pendaftaran.PendaftaranKonsultasi;
import Kelompok2_RPL.AplikasiKlinik.pendaftaran.PendaftaranKonsultasiRepository;

@Service
public class DokterService {
    @Autowired
    private PendaftaranKonsultasiRepository pendaftaranKonsultasiRepository;

    @Autowired
    private PasienDokterRepository pasienDokterRepository;

    @Autowired
    private CheckupRepository checkupRepository;

    public List<PendaftaranKonsultasi> getAllPendaftaran(int id_Dokter){
        return this.pendaftaranKonsultasiRepository.getAllPendaftaran(id_Dokter);
    }

    public List<PendaftaranKonsultasi> searchPendaftaranByName(int id_Dokter, String filter){
        return this.pendaftaranKonsultasiRepository.searchPendaftaranByName(id_Dokter, filter);
    }

    public Optional<PasienDokter> getPasienById(int id_Pasien){
        return this.pasienDokterRepository.getPasienDokterById(id_Pasien);
    }

    public Optional<Checkup> getCheckupByPasienAndCurrDate(int id_Pasien){
        return this.checkupRepository.getCheckupByPasienAndCurrDate(id_Pasien);
    }
}
