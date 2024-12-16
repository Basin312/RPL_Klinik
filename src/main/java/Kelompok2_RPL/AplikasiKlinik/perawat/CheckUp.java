package Kelompok2_RPL.AplikasiKlinik.perawat;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CheckUp {
    private Integer id;
    private String nama;
    private LocalDate tanggal;
}
