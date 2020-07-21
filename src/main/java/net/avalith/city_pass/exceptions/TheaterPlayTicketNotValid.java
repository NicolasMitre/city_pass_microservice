package net.avalith.city_pass.exceptions;

import static net.avalith.city_pass.utils.Constants.THEATERPLAY_TICKET_NOT_VALID;

public class TheaterPlayTicketNotValid extends BrokenConstraintException {
    public TheaterPlayTicketNotValid(Integer quantityTicket) {
        super(THEATERPLAY_TICKET_NOT_VALID + quantityTicket);
    }
}
