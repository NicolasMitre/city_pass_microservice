package net.avalith.city_pass.controllers;

import net.avalith.city_pass.dto.ErrorResponseDto;
import net.avalith.city_pass.exceptions.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static net.avalith.city_pass.utils.Constants.CITY_NOT_FOUND_MESSAGE;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CityNotFoundException.class)
    public ErrorResponseDto handleLoginException(CityNotFoundException exc) {
        return new ErrorResponseDto(1, CITY_NOT_FOUND_MESSAGE);
    }

}
