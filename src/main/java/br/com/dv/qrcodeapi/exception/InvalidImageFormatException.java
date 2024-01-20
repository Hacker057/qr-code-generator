package br.com.dv.qrcodeapi.exception;

public class InvalidImageFormatException extends RuntimeException {

    private static final String DEFAULT_EXCEPTION_MESSAGE = "Only png, jpeg and gif image types are supported";

    public InvalidImageFormatException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }

}
