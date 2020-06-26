package net.avalith.city_pass.exceptions;

import static net.avalith.city_pass.utils.Constants.CITY_NAME_ALREADY_USED_MESSAGE;

public class CityNameAlreadyUsedException extends BrokenConstraintException {
    public CityNameAlreadyUsedException() {
        super(CITY_NAME_ALREADY_USED_MESSAGE);
    }
}
