package Kelompok2_RPL.AplikasiKlinik.Administrator;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TransaksiServiceTest {

    @Mock
    private JdbcTransaksiRepository transaksiRepository;

    @InjectMocks
    private TransaksiService transaksiService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPasienWithStatus() {
        // Arrange
        List<PasienStatusDTO> mockPasienList = Arrays.asList(
            new PasienStatusDTO(1, "John Doe", "Belum Bayar"),
            new PasienStatusDTO(2, "Jane Doe", "Sudah Bayar")
        );
        when(transaksiRepository.findAllWithStatus()).thenReturn(mockPasienList);

        // Act
        List<PasienStatusDTO> result = transaksiService.getAllPasienWithStatus();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getNama());
        verify(transaksiRepository, times(1)).findAllWithStatus();
    }

    @Test
    public void testSearchPasienByName() {
        // Arrange
        String keyword = "John";
        List<PasienStatusDTO> mockSearchResult = Arrays.asList(
            new PasienStatusDTO(1, "John Doe", "Belum Bayar")
        );
        when(transaksiRepository.findByName(keyword)).thenReturn(mockSearchResult);

        // Act
        List<PasienStatusDTO> result = transaksiService.searchPasienByName(keyword);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNama());
        verify(transaksiRepository, times(1)).findByName(keyword);
    }

    @Test
    public void testGetDetailTransaksi() {
        // Arrange
        int idPasien = 1;
        DetailTransaksiDTO mockDetail = new DetailTransaksiDTO(
            idPasien,
            "John Doe",
            new Date(),
            BigDecimal.valueOf(50000),
            DetailTransaksiDTO.MethodBayar.TUNAI,
            false
        );
        when(transaksiRepository.findDetailTransaksiById(idPasien)).thenReturn(mockDetail);

        // Act
        DetailTransaksiDTO result = transaksiService.getDetailTransaksi(idPasien);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getNama());
        assertEquals(BigDecimal.valueOf(50000), result.getHarga());
        verify(transaksiRepository, times(1)).findDetailTransaksiById(idPasien);
    }

    @Test
    public void testUpdateTransaksiByPasien_Success() {
        // Arrange
        int idPasien = 1;
        String status = "Sudah Bayar";
        String methodBayar = "TUNAI";

        DetailTransaksiDTO mockDetail = new DetailTransaksiDTO(
            idPasien,
            "John Doe",
            new Date(),
            BigDecimal.valueOf(50000),
            DetailTransaksiDTO.MethodBayar.TUNAI,
            false
        );
        when(transaksiRepository.findDetailTransaksiById(idPasien)).thenReturn(mockDetail);

        // Act
        assertDoesNotThrow(() -> transaksiService.updateTransaksiByPasien(idPasien, status, methodBayar));

        // Assert
        verify(transaksiRepository, times(1)).updateTransaksi(idPasien, true, methodBayar);
    }

    @Test
    public void testUpdateTransaksiByPasien_ReadOnly() {
        // Arrange
        int idPasien = 1;
        String status = "Sudah Bayar";
        String methodBayar = "TUNAI";

        DetailTransaksiDTO mockDetail = new DetailTransaksiDTO(
            idPasien,
            "John Doe",
            new Date(),
            BigDecimal.valueOf(50000),
            DetailTransaksiDTO.MethodBayar.TUNAI,
            true // isReadOnly
        );
        when(transaksiRepository.findDetailTransaksiById(idPasien)).thenReturn(mockDetail);

        // Act & Assert
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            transaksiService.updateTransaksiByPasien(idPasien, status, methodBayar);
        });
        assertEquals("Transaksi ini sudah selesai dan tidak dapat diubah lagi.", exception.getMessage());
        verify(transaksiRepository, never()).updateTransaksi(anyInt(), anyBoolean(), anyString());
    }
}

