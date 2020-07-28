package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.exceptions.TheaterPlayRemainingTicketException;
import net.avalith.city_pass.exceptions.TheaterPlaySoldOutException;
import net.avalith.city_pass.models.TheaterPlayTicket;
import net.avalith.city_pass.repositories.TheaterPlayTicketRepository;
import org.springframework.stereotype.Service;

import static net.avalith.city_pass.utils.Constants.THEATERPLAY_CAPACITY;

@RequiredArgsConstructor
@Service
public class TheaterPlayTicketService {
    private final TheaterPlayTicketRepository theaterPlayTicketRepository;

    public void validate(TheaterPlayTicket productToValidate) {
        Integer ticketSold = theaterPlayTicketRepository.sumTheaterPlayTicketById(productToValidate.getTheaterPlay().getIdTheaterPlay());

        if (ticketSold.equals(Math.toIntExact(THEATERPLAY_CAPACITY))) {
            throw new TheaterPlaySoldOutException();
        } else {
            if ((ticketSold + productToValidate.getQuantity()) > THEATERPLAY_CAPACITY) {
                Integer ticketLeft = Math.toIntExact(THEATERPLAY_CAPACITY - ticketSold);
                throw new TheaterPlayRemainingTicketException(ticketLeft);
            }
        }
    }
}
