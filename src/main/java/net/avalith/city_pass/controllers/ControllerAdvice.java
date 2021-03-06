package net.avalith.city_pass.controllers;

import net.avalith.city_pass.dto.response.ErrorResponseDto;
import net.avalith.city_pass.exceptions.BrokenConstraintException;
import net.avalith.city_pass.exceptions.CityNotFoundException;
import net.avalith.city_pass.exceptions.CityPassNotFoundException;
import net.avalith.city_pass.exceptions.ExcursionNotFoundException;
import net.avalith.city_pass.exceptions.PurchaseNotFoundException;
import net.avalith.city_pass.exceptions.RoleNotFoundException;
import net.avalith.city_pass.exceptions.TheaterPlayNotFoundException;
import net.avalith.city_pass.exceptions.UserNotFoundException;
import net.avalith.city_pass.paypal.PayPalApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static net.avalith.city_pass.utils.Constants.CITY_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.CITY_PASS_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.EXCURSION_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.PAYPAL_API_ERROR_MESSAGE;
import static net.avalith.city_pass.utils.Constants.PURCHASE_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.ROLE_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.THEATERPLAY_NOT_FOUND_MESSAGE;
import static net.avalith.city_pass.utils.Constants.USER_NOT_FOUND_MESSAGE;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CityNotFoundException.class)
    public ErrorResponseDto handleCityNotFoundException(CityNotFoundException exc) {
        return new ErrorResponseDto(CITY_NOT_FOUND_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public ErrorResponseDto handleRoleNotFoundException(RoleNotFoundException exc) {
        return new ErrorResponseDto(ROLE_NOT_FOUND_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TheaterPlayNotFoundException.class)
    public ErrorResponseDto handleTheaterPlayNotFoundException(TheaterPlayNotFoundException exc) {
        return new ErrorResponseDto(THEATERPLAY_NOT_FOUND_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponseDto handleUserNotFoundException(UserNotFoundException exc) {
        return new ErrorResponseDto(USER_NOT_FOUND_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CityPassNotFoundException.class)
    public ErrorResponseDto handleCityPassNotFoundException(CityPassNotFoundException exc) {
        return new ErrorResponseDto(CITY_PASS_NOT_FOUND_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ExcursionNotFoundException.class)
    public ErrorResponseDto handleExcursionNotFoundException(ExcursionNotFoundException exc) {
        return new ErrorResponseDto(EXCURSION_NOT_FOUND_MESSAGE);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BrokenConstraintException.class)
    public ErrorResponseDto handleBrokenConstraintException(BrokenConstraintException exc) {
        return new ErrorResponseDto(exc.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PurchaseNotFoundException.class)
    public ErrorResponseDto handlePurchaseNotFoundException(PurchaseNotFoundException exc) {
        return new ErrorResponseDto(PURCHASE_NOT_FOUND_MESSAGE);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PayPalApiException.class)
    public ErrorResponseDto handlePayPalApiException(PayPalApiException exc) {
        return new ErrorResponseDto(exc.getLocalizedMessage());
    }

}
