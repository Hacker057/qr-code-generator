package br.com.dv.qrcodeapi.exception;

public class InvalidContentException extends RuntimeException {

    private static final String DEFAULT_EXCEPTION_MESSAGE = "Contents cannot be null or blank";

    public InvalidContentException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }

}
