package Kelompok2_RPL.AplikasiKlinik.dokter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kelompok2_RPL.AplikasiKlinik.catatan_obat.CatatanObat;
import Kelompok2_RPL.AplikasiKlinik.catatan_obat.CatatanObatRepository;
import Kelompok2_RPL.AplikasiKlinik.checkup.Checkup;
import Kelompok2_RPL.AplikasiKlinik.checkup.CheckupRepository;
import Kelompok2_RPL.AplikasiKlinik.dokumen_pendukung.DokumenPendukung;
import Kelompok2_RPL.AplikasiKlinik.dokumen_pendukung.DokumenPendukungRepository;
import Kelompok2_RPL.AplikasiKlinik.konsultasi.Konsultasi;
import Kelompok2_RPL.AplikasiKlinik.konsultasi.KonsultasiRepository;
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

    @Autowired
    private KonsultasiRepository konsultasiRepository;

    @Autowired
    private CatatanObatRepository catatanObatRepository;

    @Autowired
    private DokumenPendukungRepository dokumenPendukungRepository;

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
        return this.checkupRepository.getCheckupByPasienAndCurrDate(id_Pasien); //nampilin yang terbaru aja
    }

    public void tambahKonsultasi(String diagnosa, int id_Dokter, int id_Checkup){
        this.konsultasiRepository.tambahKonsultasi(diagnosa, id_Dokter, id_Checkup);
    }

    public Optional<Konsultasi> getKonsultasiTerbaruByDokterAndCurrDate(int id_Dokter){
        return this.konsultasiRepository.getKonsultasiTerbaruByDokterAndCurrDate(id_Dokter);
    }

    public void tambahObat(String obat, String dosis, int id_Konsul){
        this.catatanObatRepository.tambahObat(obat, dosis, id_Konsul);
    }

    public List<Checkup> getCheckupByPasien(int id_Pasien){
        return this.checkupRepository.getCheckupByPasien(id_Pasien);
    }

    public List<Konsultasi> getKonsultasiByPasien(int id_Pasien){
        return this.konsultasiRepository.getKonsultasiByPasien(id_Pasien);
    }

    public List<DokumenPendukung> getDokumenPendukungByPasien(int id_Pasien){
        return this.dokumenPendukungRepository.getDokumenPendukungByPasien(id_Pasien);
    }

    public List<CatatanObat> getCatatanObatByPasien(int id_Pasien){
        return this.catatanObatRepository.getCatatanObatByPasien(id_Pasien);
    }

    public void setIsKonsul(int id_Pendaftaran){
        this.pendaftaranKonsultasiRepository.setIsKonsul(id_Pendaftaran);
    }

    public Optional<DokumenPendukung> getDokumenById(int id_Dokumen){
        return this.dokumenPendukungRepository.getDokumenById(id_Dokumen);
    } 
}
