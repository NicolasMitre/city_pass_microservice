package net.avalith.city_pass.exceptions;

import static net.avalith.city_pass.utils.Constants.THEATERPLAY_REMAINING_TICKET;

public class TheaterPlayRemainingTicketException extends BrokenConstraintException {
    public TheaterPlayRemainingTicketException(Integer ticketLeft) {
        super(THEATERPLAY_REMAINING_TICKET + ticketLeft);
    }
}
