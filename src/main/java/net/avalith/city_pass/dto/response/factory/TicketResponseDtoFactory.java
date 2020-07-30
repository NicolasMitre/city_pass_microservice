package net.avalith.city_pass.dto.response.factory;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.response.CityPassTicketResponseDto;
import net.avalith.city_pass.dto.response.ExcursionTicketResponseDto;
import net.avalith.city_pass.dto.response.TheaterPlayTicketResponseDto;
import net.avalith.city_pass.dto.response.TicketResponseDto;
import net.avalith.city_pass.models.CityPassTicket;
import net.avalith.city_pass.models.ExcursionTicket;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.models.TheaterPlayTicket;
import net.avalith.city_pass.models.Ticket;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketResponseDtoFactory implements BaseTicketResponseDtoFactory {

    @Override
    public TicketResponseDto getTicketResponseDto(Ticket ticket, Purchase purchase) {
        TicketResponseDto ticketResponseDto = null;
        switch (ticket.getTicketType()) {
            case citypass:
                CityPassTicket cityPassTicket = (CityPassTicket) ticket;
                ticketResponseDto = CityPassTicketResponseDto.createCityPassTicketResponseDto(cityPassTicket);
                break;
            case theaterplay:
                TheaterPlayTicket theaterPlayTicket = (TheaterPlayTicket) ticket;
                ticketResponseDto = TheaterPlayTicketResponseDto.createTheaterPlayTicketResponseDto(theaterPlayTicket);
                break;
            case excursion:
                ExcursionTicket excursionTicket = (ExcursionTicket) ticket;
                ticketResponseDto = ExcursionTicketResponseDto.createExcursionTicketResponseDto(excursionTicket);
                break;
        }
        return ticketResponseDto;
    }
}
