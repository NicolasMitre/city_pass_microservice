package net.avalith.city_pass.models.factory;

import net.avalith.city_pass.dto.TicketDto;
import net.avalith.city_pass.models.Ticket;

public interface BaseTicketFactory {
    Ticket getTicket(TicketDto ticketDto);
}
