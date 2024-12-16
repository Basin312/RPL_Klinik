package Kelompok2_RPL.AplikasiKlinik.jadwal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Jadwal {
    private int idJadwal;
    private String namaDokter;
    private String namaSpecialis;
    private int idHari;
    private String jam;
    private int sisaSlot;
}

