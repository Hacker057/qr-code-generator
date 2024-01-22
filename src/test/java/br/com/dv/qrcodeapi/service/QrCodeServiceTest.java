package br.com.dv.qrcodeapi.service;

import br.com.dv.qrcodeapi.exception.InvalidCorrectionLevelException;
import br.com.dv.qrcodeapi.validation.ParameterValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class QrCodeServiceTest {

    @Mock
    private ParameterValidator parameterValidator;

    @InjectMocks
    private QrCodeServiceImpl qrCodeService;

    @Test
    public void generateQrCode_validParameters_returnsImageResponse() {
        doNothing().when(parameterValidator).validate("content", 250, "L", "png");

        var qrCode = qrCodeService.generateQrCode("content", 250, "L", "png");

        assertNotNull(qrCode);
        assertNotNull(qrCode.imageData());
        assertEquals(MediaType.IMAGE_PNG, qrCode.mediaType());
    }

    @Test
    void Invalid_Correction_Level_Parameter_Throws_Invalid_Correction_Level_Exception() {
        doNothing().when(parameterValidator).validate(anyString(), anyInt(), anyString(), anyString());

        assertThrows(InvalidCorrectionLevelException.class,
                () -> qrCodeService.generateQrCode("content", 250, "invalid", "png"));
    }

}
