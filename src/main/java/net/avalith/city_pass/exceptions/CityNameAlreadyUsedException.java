package net.avalith.city_pass.exceptions;

public class CityNameAlreadyUsedException extends BrokenConstraintException {
    public CityNameAlreadyUsedException(String message) {
        super(message);
    }
}
