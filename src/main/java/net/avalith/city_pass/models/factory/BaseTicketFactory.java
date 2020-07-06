package net.avalith.city_pass.models.factory;

import net.avalith.city_pass.dto.request.TicketRequestDto;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.models.Ticket;

public interface BaseTicketFactory {
    Ticket getTicket(TicketRequestDto ticketRequestDto, Purchase purchase);
}
