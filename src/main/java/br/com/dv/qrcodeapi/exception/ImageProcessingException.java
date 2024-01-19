package br.com.dv.qrcodeapi.exception;

public class ImageProcessingException extends RuntimeException {

    private static final String DEFAULT_EXCEPTION_MESSAGE = "Error while generating image";

    public ImageProcessingException(Throwable cause) {
        super(DEFAULT_EXCEPTION_MESSAGE, cause);
    }

}
