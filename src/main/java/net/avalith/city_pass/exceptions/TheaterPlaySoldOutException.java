package net.avalith.city_pass.exceptions;

import static net.avalith.city_pass.utils.Constants.THEATERPLAY_TICKET_SOLD_OUT;

public class TheaterPlaySoldOutException extends BrokenConstraintException {
    public TheaterPlaySoldOutException() {
        super(THEATERPLAY_TICKET_SOLD_OUT);
    }
}
