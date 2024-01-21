package br.com.dv.qrcodeapi.exception;

public class InvalidCorrectionLevelException extends RuntimeException {

    private static final String DEFAULT_EXCEPTION_MESSAGE = "Permitted error correction levels are L, M, Q, H";

    public InvalidCorrectionLevelException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }

}
