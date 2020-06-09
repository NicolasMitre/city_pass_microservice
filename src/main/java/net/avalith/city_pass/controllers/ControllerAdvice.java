package net.avalith.city_pass.controllers;

import net.avalith.city_pass.dto.ErrorResponseDto;
import net.avalith.city_pass.exceptions.CityNotFoundException;
import net.avalith.city_pass.exceptions.RoleNotFoundException;
import net.avalith.city_pass.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static net.avalith.city_pass.utils.Constants.CITY_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.ROLE_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.USER_NOT_FOUND_MESSAGE;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CityNotFoundException.class)
    public ErrorResponseDto handleLoginException(CityNotFoundException exc) {
        return new ErrorResponseDto(1, CITY_NOT_FOUND_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public ErrorResponseDto handleLoginException(RoleNotFoundException exc) {
        return new ErrorResponseDto(1, ROLE_NOT_FOUND_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponseDto handleLoginException(UserNotFoundException exc){
        return new ErrorResponseDto(1,USER_NOT_FOUND_MESSAGE);
    }
}
