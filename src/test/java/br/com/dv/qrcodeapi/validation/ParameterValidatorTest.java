package br.com.dv.qrcodeapi.validation;

import br.com.dv.qrcodeapi.exception.InvalidContentException;
import br.com.dv.qrcodeapi.exception.InvalidCorrectionLevelException;
import br.com.dv.qrcodeapi.exception.InvalidImageFormatException;
import br.com.dv.qrcodeapi.exception.InvalidImageSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParameterValidatorTest {

    private ParameterValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ParameterValidator();
    }

    @Test
    void Valid_Content_Does_Not_Throw_Exception() {
        assertDoesNotThrow(() -> validator.validate("content", 250, "L", "png"));
    }

    @Test
    void Null_Content_Throws_Invalid_Content_Exception() {
        assertThrows(InvalidContentException.class,
                () -> validator.validate(null, 250, "L", "png"));
    }

    @Test
    void Blank_Content_Throws_Invalid_Content_Exception() {
        assertThrows(InvalidContentException.class,
                () -> validator.validate("", 250, "L", "png"));
    }

    @Test
    void Valid_Size_Does_Not_Throw_Exception() {
        assertDoesNotThrow(() -> validator.validate("content", 250, "L", "png"));
    }

    @Test
    void Too_Small_Size_Throws_Invalid_Image_Size_Exception() {
        assertThrows(InvalidImageSizeException.class,
                () -> validator.validate("content", 100, "L", "png"));
    }

    @Test
    void Too_Large_Size_Throws_Invalid_Image_Size_Exception() {
        assertThrows(InvalidImageSizeException.class,
                () -> validator.validate("content", 400, "L", "png"));
    }

    @Test
    void Valid_Correction_Level_Does_Not_Throw_Exception() {
        assertDoesNotThrow(() -> validator.validate("content", 250, "L", "png"));
    }

    @Test
    void Invalid_Correction_Level_Throws_Invalid_Correction_Level_Exception() {
        assertThrows(InvalidCorrectionLevelException.class,
                () -> validator.validate("content", 250, "invalid", "png"));
    }

    @Test
    void Valid_Format_Does_Not_Throw_Exception() {
        assertDoesNotThrow(() -> validator.validate("content", 250, "L", "png"));
    }

    @Test
    void Invalid_Format_Throws_Invalid_Image_Format_Exception() {
        assertThrows(InvalidImageFormatException.class,
                () -> validator.validate("content", 250, "L", "invalid"));
    }

}
