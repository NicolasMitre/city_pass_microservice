package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.request.TicketRequestDto;
import net.avalith.city_pass.models.Ticket;
import net.avalith.city_pass.models.factory.TicketFactory;
import net.avalith.city_pass.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketFactory ticketFactory;
    private final TicketRepository ticketRepository;

    public Ticket processTicket(TicketRequestDto ticketRequestDto, LocalDateTime purchaseDate) {
        return ticketFactory.getTicket(ticketRequestDto,purchaseDate);
    }

    public List<Ticket> persistTicket(@NotEmpty List<@Valid Ticket> tickets){
        return ticketRepository.saveAll(tickets);
    }

    public List<Ticket> getByPurchaseId(Integer idPurchase) {
        return ticketRepository.getAllTicketsByPurchaseId(idPurchase);
    }
}