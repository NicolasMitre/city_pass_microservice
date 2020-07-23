package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.exceptions.TheaterPlayRemainingTicketException;
import net.avalith.city_pass.exceptions.TheaterPlaySoldOutException;
import net.avalith.city_pass.repositories.TheaterPlayTicketRepository;
import org.springframework.stereotype.Service;

import static net.avalith.city_pass.utils.Constants.THEATERPLAY_CAPACITY;

@RequiredArgsConstructor
@Service
public class TheaterPlayTicketService {
    private final TheaterPlayTicketRepository theaterPlayTicketRepository;

    public void validate(Integer idTicketPlay, Integer quantityTicket) {
        Integer ticketSold = theaterPlayTicketRepository.sumTheaterPlayTicketById(idTicketPlay);

        if (ticketSold.equals(Math.toIntExact(THEATERPLAY_CAPACITY))) {
            throw new TheaterPlaySoldOutException();
        } else {
            if ((ticketSold + quantityTicket) > THEATERPLAY_CAPACITY) {
                Integer ticketLeft = Math.toIntExact(THEATERPLAY_CAPACITY - ticketSold);
                throw new TheaterPlayRemainingTicketException(ticketLeft);
            }
        }
    }
}
