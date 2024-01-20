package br.com.dv.qrcodeapi.service;

import br.com.dv.qrcodeapi.dto.ImageResponse;
import br.com.dv.qrcodeapi.util.ImageUtils;
import br.com.dv.qrcodeapi.validation.ParameterValidator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    private final ParameterValidator parameterValidator;

    public QrCodeServiceImpl(ParameterValidator parameterValidator) {
        this.parameterValidator = parameterValidator;
    }

    @Override
    public ImageResponse generateImage(int size, String format) {
        this.parameterValidator.validate(size, format);

        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = ImageUtils.configureGraphics(image);

        try {
            byte[] imageData = ImageUtils.writeImageToByteArray(image, format);
            MediaType mediaType = ImageUtils22.getMediaTypeForImageFormat(format);
            return new ImageResponse(imageData, mediaType);
        } finally {
            graphics.dispose();
        }
    }

}
