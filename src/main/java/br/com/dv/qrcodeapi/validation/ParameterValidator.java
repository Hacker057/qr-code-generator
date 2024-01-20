package br.com.dv.qrcodeapi.validation;

import br.com.dv.qrcodeapi.exception.InvalidImageSizeException;
import org.springframework.stereotype.Component;

@Component
public class ParameterValidator {

    private static final int MIN_IMAGE_SIZE = 150;
    private static final int MAX_IMAGE_SIZE = 350;

    public void validateImageSize(int size) {
        if (isSizeInvalid(size)) {
            throw new InvalidImageSizeException();
        }
    }

    private boolean isSizeInvalid(int size) {
        return size < MIN_IMAGE_SIZE || size > MAX_IMAGE_SIZE;
    }

}
