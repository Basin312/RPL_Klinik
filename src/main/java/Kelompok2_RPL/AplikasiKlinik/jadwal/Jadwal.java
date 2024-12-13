package Kelompok2_RPL.AplikasiKlinik.jadwal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Jadwal {
    private String namaDokter;
    private String namaSpecialis;
    private String hari;
    private String jam;
    private int sisaSlot;
}