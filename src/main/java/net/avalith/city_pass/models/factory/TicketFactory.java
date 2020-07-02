package net.avalith.city_pass.models.factory;

import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.TicketDto;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.models.CityPassTicket;
import net.avalith.city_pass.models.TheaterPlay;
import net.avalith.city_pass.models.TheaterPlayTicket;
import net.avalith.city_pass.models.Ticket;
import net.avalith.city_pass.services.CityPassService;
import net.avalith.city_pass.services.TheaterPlayService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TicketFactory implements BaseTicketFactory {
    private final CityPassService cityPassService;
    //private final ExcursionService excursionService;
    private final TheaterPlayService theaterPlayService;

    @Override
    public Ticket getTicket(TicketDto ticketDto) {
        Ticket ticket = null;
        LocalDateTime lt = LocalDateTime.now();

        String code = Hashing.sha256().hashString(ticketDto.getIdProduct() + lt.toString(), StandardCharsets.UTF_8).toString();
        switch (ticketDto.getTicketType()) {
            case citypass:
                CityPass cityPass = cityPassService.getById(ticketDto.getIdProduct());

                ticket = CityPassTicket.builder()
                        .ticketType(ticketDto.getTicketType())
                        .cityPass(cityPass)
                        .quantity(ticketDto.getQuantity())
                        .subTotal(ticketDto.getQuantity() * cityPass.getPrice())
                        .code(code)
                        .build();
                break;
            case theaterplay:
                TheaterPlay theaterPlay = theaterPlayService.getById(ticketDto.getIdProduct());

                ticket = TheaterPlayTicket.builder()
                        .ticketType(ticketDto.getTicketType())
                        .theaterPlay(theaterPlay)
                        .quantity(ticketDto.getQuantity())
                        .subTotal(ticketDto.getQuantity() * theaterPlay.getPrice())
                        .code(code)
                        .build();
                break;
            /*case excursion:
                Excursion excursion = excursionService.getById(ticketDto.getIdProduct());
                code = CodeGenerator.generateTicketCode(Arrays.asList(String.valueOf(cityPass.getId()),String.valueOf(lt));

                break;*/
        }

        return ticket;
    }
}
