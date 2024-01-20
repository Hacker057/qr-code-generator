package br.com.dv.qrcodeapi.validation;

import br.com.dv.qrcodeapi.enums.ImageFormat;
import br.com.dv.qrcodeapi.exception.InvalidContentException;
import br.com.dv.qrcodeapi.exception.InvalidImageFormatException;
import br.com.dv.qrcodeapi.exception.InvalidImageSizeException;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Set;

@Component
public class ParameterValidator {

    private static final int MIN_IMAGE_SIZE = 150;
    private static final int MAX_IMAGE_SIZE = 350;
    private static final Set<ImageFormat> SUPPORTED_IMAGE_FORMATS = EnumSet.allOf(ImageFormat.class);

    public void validate(String content, int size, String format) {
        validateContent(content);
        validateImageSize(size);
        validateImageFormat(format);
    }

    private void validateContent(String content) {
        if (isContentInvalid(content)) {
            throw new InvalidContentException();
        }
    }

    private void validateImageSize(int size) {
        if (isSizeInvalid(size)) {
            throw new InvalidImageSizeException();
        }
    }

    private void validateImageFormat(String format) {
        if (isFormatInvalid(format)) {
            throw new InvalidImageFormatException();
        }
    }

    private boolean isContentInvalid(String content) {
        return content == null || content.isBlank();
    }

    private boolean isSizeInvalid(int size) {
        return size < MIN_IMAGE_SIZE || size > MAX_IMAGE_SIZE;
    }

    private boolean isFormatInvalid(String format) {
        return format == null || SUPPORTED_IMAGE_FORMATS.stream().noneMatch(f -> f.name().equalsIgnoreCase(format));
    }

}
