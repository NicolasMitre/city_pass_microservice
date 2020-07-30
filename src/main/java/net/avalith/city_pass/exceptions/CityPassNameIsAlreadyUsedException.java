package net.avalith.city_pass.exceptions;

import static net.avalith.city_pass.utils.Constants.CITY_PASS_NAME_IS_ALREADY_USED;

public class CityPassNameIsAlreadyUsedException extends BrokenConstraintException{
    public CityPassNameIsAlreadyUsedException(){ super(CITY_PASS_NAME_IS_ALREADY_USED); }
}
