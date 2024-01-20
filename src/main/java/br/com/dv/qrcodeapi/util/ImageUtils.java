package br.com.dv.qrcodeapi.util;

import br.com.dv.qrcodeapi.enums.ImageFormat;
import br.com.dv.qrcodeapi.exception.ImageProcessingException;
import br.com.dv.qrcodeapi.exception.InvalidImageFormatException;
import org.springframework.http.MediaType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public final class ImageUtils {

    private static final String CANNOT_INSTANTIATE_CLASS_MESSAGE = "This class should not be instantiated";

    private ImageUtils() {
        throw new IllegalStateException(CANNOT_INSTANTIATE_CLASS_MESSAGE);
    }

    public static byte[] writeImageToByteArray(BufferedImage image, String format) {
        try (var outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, format, outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new ImageProcessingException(e);
        }
    }

    public static Graphics2D configureGraphics(BufferedImage image) {
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(image.getMinX(), image.getMinY(), image.getWidth(), image.getHeight());
        return graphics;
    }

    public static MediaType getMediaTypeForImageFormat(String format) {
        return Arrays.stream(ImageFormat.values())
                .filter(f -> f.name().equalsIgnoreCase(format))
                .findFirst()
                .map(ImageFormat::getMediaType)
                .orElseThrow(InvalidImageFormatException::new);
    }

}
