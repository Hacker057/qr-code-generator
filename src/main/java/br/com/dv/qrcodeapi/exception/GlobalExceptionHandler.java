package br.com.dv.qrcodeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ImageProcessingException.class)
    public ResponseEntity<ApiError> handleImageProcessingException(ImageProcessingException e) {
        return getResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidImageSizeException.class)
    public ResponseEntity<ApiError> handleInvalidImageSizeException(Exception e) {
        return getResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiError> getResponseEntity(Exception e, HttpStatus status) {
        ApiError error = new ApiError(e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    public record ApiError(String error) {
    }

}
