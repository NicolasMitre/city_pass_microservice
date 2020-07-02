package net.avalith.city_pass.exceptions;

import static net.avalith.city_pass.utils.Constants.EXCURSION_NAME_ALREADY_USED_MESSAGE;

public class ExcursionNameAlreadyUsedException extends BrokenConstraintException {
    public ExcursionNameAlreadyUsedException() {
        super(EXCURSION_NAME_ALREADY_USED_MESSAGE);
    }
}
