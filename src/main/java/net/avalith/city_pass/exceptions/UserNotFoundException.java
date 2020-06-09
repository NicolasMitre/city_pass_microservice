package net.avalith.city_pass.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){super();}
}
