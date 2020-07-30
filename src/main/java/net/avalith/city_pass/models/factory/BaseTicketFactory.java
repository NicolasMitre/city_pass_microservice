package net.avalith.city_pass.models.factory;

import net.avalith.city_pass.dto.request.TicketRequestDto;
import net.avalith.city_pass.models.Ticket;

import java.time.LocalDateTime;

public interface BaseTicketFactory {
    Ticket getTicket(TicketRequestDto ticketRequestDto, LocalDateTime purchaseDate);
}
