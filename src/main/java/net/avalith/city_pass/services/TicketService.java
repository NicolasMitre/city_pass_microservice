package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.TicketDto;
import net.avalith.city_pass.exceptions.UserNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.models.CityPassTicket;
import net.avalith.city_pass.models.Excursion;
import net.avalith.city_pass.models.TheaterPlay;
import net.avalith.city_pass.models.TheaterPlayTicket;
import net.avalith.city_pass.models.Ticket;
import net.avalith.city_pass.models.factory.TicketFactory;
import net.avalith.city_pass.repositories.CityPassTicketRepository;
import net.avalith.city_pass.repositories.TheaterPlayTicketRepository;
import net.avalith.city_pass.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketFactory ticketFactory;
    private final TicketRepository ticketRepository;

    public Ticket processData(TicketDto ticketDto) {
        Ticket productBought = ticketFactory.getTicket(ticketDto);
        return ticketRepository.save(productBought);
    }


}
