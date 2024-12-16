package Kelompok2_RPL.AplikasiKlinik.jadwal;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtilities {
    public static LocalDate calculateDate(int idHari) {
        LocalDate today = LocalDate.now();
        LocalDate targetDate = today.with(DayOfWeek.of(idHari));
        if (targetDate.isBefore(today)) {
            targetDate = targetDate.plusWeeks(1);
        }
        return targetDate;
    }
}
