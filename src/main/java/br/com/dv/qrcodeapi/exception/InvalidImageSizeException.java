package br.com.dv.qrcodeapi.exception;

public class InvalidImageSizeException extends RuntimeException {

    private static final String DEFAULT_EXCEPTION_MESSAGE = "Image size must be between 150 and 350 pixels";

    public InvalidImageSizeException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }

}
