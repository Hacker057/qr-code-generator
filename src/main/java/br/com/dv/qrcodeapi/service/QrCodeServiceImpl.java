package br.com.dv.qrcodeapi.service;

import br.com.dv.qrcodeapi.dto.ImageResponse;
import br.com.dv.qrcodeapi.exception.ImageProcessingException;
import br.com.dv.qrcodeapi.util.ImageUtils;
import br.com.dv.qrcodeapi.validation.ParameterValidator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class QrCodeServiceImpl implements QrCodeService {
    
    private final ParameterValidator parameterValidator;
    private final QRCodeWriter qrCodeWriter = new QRCodeWriter();

    public QrCodeServiceImpl(ParameterValidator parameterValidator) {
        this.parameterValidator = parameterValidator;
    }

    @Override
    public ImageResponse generateQrCode(String content, int size, String format) {
        this.parameterValidator.validate(content, size, format);

        BufferedImage qrCodeImage = generateQrCodeImage(content, size);
        byte[] qrCodeImageData = ImageUtils.writeImageToByteArray(qrCodeImage, format);
        MediaType mediaType = ImageUtils.getMediaTypeForImageFormat(format);

        return new ImageResponse(qrCodeImageData, mediaType);
    }

    private BufferedImage generateQrCodeImage(String content, int size) {
        try {
            BitMatrix bitMatrix = this.qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            throw new ImageProcessingException(e);
        }
    }

}
