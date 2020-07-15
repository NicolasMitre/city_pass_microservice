package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.request.TicketRequestDto;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.models.Ticket;
import net.avalith.city_pass.models.factory.TicketFactory;
import net.avalith.city_pass.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketFactory ticketFactory;
    private final TicketRepository ticketRepository;

    public Ticket processTicket(TicketRequestDto ticketRequestDto, Purchase purchase) {
        Ticket productBought = ticketFactory.getTicket(ticketRequestDto, purchase);
        return ticketRepository.save(productBought);
    }

    public List<Ticket> getByPurchaseId(Integer idPurchase) {
        return ticketRepository.getAllTicketsByPurchaseId(idPurchase);
    }
}