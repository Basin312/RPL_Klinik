package Kelompok2_RPL.AplikasiKlinik.Administrator;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailTransaksiDTO {
    private int idPasien;
    private String nama;
    private Date tanggalKonsultasi;
    private BigDecimal harga;
    private MethodBayar methodBayar;

    public enum MethodBayar {
        TUNAI("TUNAI"),
        NON_TUNAI("NON_TUNAI");

        private final String value;

        MethodBayar(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static MethodBayar fromValue(String value) {
            for (MethodBayar mb : MethodBayar.values()) {
                if (mb.value.equalsIgnoreCase(value)) {
                    return mb;
                }
            }
            throw new IllegalArgumentException("No enum constant for value: " + value);
        }
    }
}
