package net.avalith.city_pass.controllers;

import net.avalith.city_pass.dto.ErrorResponseDto;
import net.avalith.city_pass.exceptions.CityNotFoundException;
import net.avalith.city_pass.exceptions.CityPassNotFoundException;
import net.avalith.city_pass.exceptions.ExcursionNameAlreadyUsedException;
import net.avalith.city_pass.exceptions.ExcursionNotFoundException;
import net.avalith.city_pass.exceptions.RoleNotFoundException;
import net.avalith.city_pass.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.avalith.city_pass.utils.Constants.CITY_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.CITY_PASS_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.EXCURSION_NAME_ALREADY_USED_MESSAGE;
import static net.avalith.city_pass.utils.Constants.EXCURSION_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.ROLE_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.USER_NOT_FOUND_MESSAGE;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler{

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CityNotFoundException.class)
    public ErrorResponseDto handleLoginException(CityNotFoundException exc) {
        return new ErrorResponseDto(Arrays.asList(CITY_NOT_FOUND_MESSAGE));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public ErrorResponseDto handleLoginException(RoleNotFoundException exc) {
        return new ErrorResponseDto(Arrays.asList(ROLE_NOT_FOUND_MESSAGE));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponseDto handleLoginException(UserNotFoundException exc) {
        return new ErrorResponseDto(Arrays.asList(USER_NOT_FOUND_MESSAGE));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CityPassNotFoundException.class)
    public ErrorResponseDto handleLoginException(CityPassNotFoundException exc) {
        return new ErrorResponseDto(Arrays.asList(CITY_PASS_NOT_FOUND_MESSAGE));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ExcursionNotFoundException.class)
    public ErrorResponseDto handleLoginException(ExcursionNotFoundException exc) {
        return new ErrorResponseDto(Arrays.asList(EXCURSION_NOT_FOUND_MESSAGE));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ExcursionNameAlreadyUsedException.class)
    public ErrorResponseDto handleLoginException(ExcursionNameAlreadyUsedException exc) {
        return new ErrorResponseDto(Arrays.asList(EXCURSION_NAME_ALREADY_USED_MESSAGE));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(errorList);
        return ResponseEntity.badRequest().body(errorResponseDto);
    }
}
