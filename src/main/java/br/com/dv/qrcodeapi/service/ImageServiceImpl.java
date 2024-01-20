package br.com.dv.qrcodeapi.service;

import br.com.dv.qrcodeapi.util.ImageUtils;
import br.com.dv.qrcodeapi.validation.ParameterValidator;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class ImageServiceImpl implements ImageService {

    private static final String IMAGE_TYPE = "png";

    private final ParameterValidator parameterValidator;

    public ImageServiceImpl(ParameterValidator parameterValidator) {
        this.parameterValidator = parameterValidator;
    }

    @Override
    public byte[] generateImage(int size) {
        this.parameterValidator.validateImageSize(size);

        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = ImageUtils.configureGraphics(image);

        try {
            return ImageUtils.writeImageToByteArray(image, IMAGE_TYPE);
        } finally {
            graphics.dispose();
        }
    }

}
