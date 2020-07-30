package net.avalith.city_pass.dto.response.factory;

import net.avalith.city_pass.dto.response.TicketResponseDto;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.models.Ticket;

public interface BaseTicketResponseDtoFactory {
    TicketResponseDto getTicketResponseDto(Ticket ticket, Purchase purchase);
}
