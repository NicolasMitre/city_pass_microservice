package net.avalith.city_pass.models.factory;

import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.request.TicketRequestDto;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.models.CityPassTicket;
import net.avalith.city_pass.models.Excursion;
import net.avalith.city_pass.models.ExcursionTicket;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.models.TheaterPlay;
import net.avalith.city_pass.models.TheaterPlayTicket;
import net.avalith.city_pass.models.Ticket;
import net.avalith.city_pass.models.enums.TicketType;
import net.avalith.city_pass.services.CityPassService;
import net.avalith.city_pass.services.ExcursionService;
import net.avalith.city_pass.services.TheaterPlayService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TicketFactory implements BaseTicketFactory {
    private final CityPassService cityPassService;
    private final ExcursionService excursionService;
    private final TheaterPlayService theaterPlayService;

    @Override
    public Ticket getTicket(TicketRequestDto ticketRequestDto, Purchase purchase) {
        Ticket ticket = null;
        LocalDateTime lt = LocalDateTime.now();
        TicketType ticketType = ticketRequestDto.getTicketType();
        Integer quantity = ticketRequestDto.getQuantity();

        Double subTotal;
        String code = Hashing.sha256().hashString(ticketRequestDto.getIdProduct() + lt.toString(), StandardCharsets.UTF_8).toString();

        switch (ticketType) {
            case citypass:
                CityPass cityPass = cityPassService.getById(ticketRequestDto.getIdProduct());
                subTotal = getSubtotal(ticketType, quantity, cityPass.getPrice());

                ticket = CityPassTicket.createCityPassTicket(ticketType,cityPass,quantity,subTotal,code,purchase);
                break;
            case theaterplay:
                TheaterPlay theaterPlay = theaterPlayService.getById(ticketRequestDto.getIdProduct());
                subTotal = getSubtotal(ticketType, quantity, theaterPlay.getPrice());

                ticket = TheaterPlayTicket.createTheaterPlayTicket(ticketType,theaterPlay,quantity,subTotal,code,purchase);
                break;
            case excursion:
                Excursion excursion = excursionService.getById(ticketRequestDto.getIdProduct());
                subTotal = getSubtotal(ticketType, quantity, excursion.getPrice());

                ticket = ExcursionTicket.createExcursionTicket(ticketType,excursion,quantity,subTotal,code,purchase);
                break;
        }
        return ticket;
    }

    private Double getSubtotal(TicketType ticketType, Integer quantity, Double price) {
        Double subTotal = price * quantity;
        if (ticketType.equals(TicketType.excursion)) {
            if (quantity >= 4) {
                subTotal *= 0.9;
            }
        }
        return subTotal;
    }
}
