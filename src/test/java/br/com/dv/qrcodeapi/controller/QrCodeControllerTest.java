package br.com.dv.qrcodeapi.controller;

import br.com.dv.qrcodeapi.dto.ImageResponse;
import br.com.dv.qrcodeapi.service.QrCodeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QrCodeController.class)
@ExtendWith(MockitoExtension.class)
public class QrCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QrCodeService qrCodeService;

    @Test
    public void Qr_Code_Generation_With_Provided_Parameters_Returns_Ok_Status() throws Exception {
        var mockResponse = new ImageResponse(new byte[0], MediaType.IMAGE_PNG);
        when(qrCodeService.generateQrCode(anyString(), anyInt(), anyString(), anyString())).thenReturn(mockResponse);

        var response = mockMvc.perform(get("/api/qrcode")
                        .param("contents", "test")
                        .param("size", "250")
                        .param("correction", "L")
                        .param("type", "png"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertEquals(MediaType.IMAGE_PNG_VALUE, response.getContentType());
    }

    @Test
    public void Qr_Code_Generation_With_Missing_Optional_Parameters_Returns_Ok_Status() throws Exception {
        var mockResponse = new ImageResponse(new byte[0], MediaType.IMAGE_PNG);
        when(qrCodeService.generateQrCode(anyString(), anyInt(), anyString(), anyString())).thenReturn(mockResponse);

        var response = mockMvc.perform(get("/api/qrcode")
                        .param("contents", "test"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertEquals(MediaType.IMAGE_PNG_VALUE, response.getContentType());
    }

    @Test
    public void Qr_Code_Generation_With_Non_Numeric_Size_Parameter_Returns_Bad_Request() throws Exception {
        mockMvc.perform(get("/api/qrcode")
                        .param("contents", "test")
                        .param("size", "n")
                        .param("correction", "L")
                        .param("type", "png"))
                .andExpect(status().isBadRequest());
    }

}
