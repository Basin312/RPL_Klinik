package Kelompok2_RPL.AplikasiKlinik.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    @Autowired
    private JdbcHomeRepository homeRepository;

    public Home getNomorAntrian(int idPasien) {Home home = homeRepository.findNomorAntrian(idPasien);
        if (home == null) {
            return new Home(0);
        }
        return home;
    }
}