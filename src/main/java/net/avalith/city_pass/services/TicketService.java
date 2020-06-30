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
    private final CityPassService cityPassService;
    //private final ExcursionService excursionService;
    private final TheaterPlayService theaterPlayService;
    private final TicketRepository ticketRepository;
    private final TheaterPlayTicketRepository theaterPlayTicketRepository;
    private final CityPassTicketRepository cityPassTicketRepository;

    /*realizarCOmpra(PurchaseDto d){
        foreach de la lista aplicandole process de ticketServce
        cada objeto "ticket" devuelto lo agrego a la lista del Domain Purchase
        mando a guardar la purchase
    }*/


    private String generateTicketCode(List<?> elements) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        for(Object o : elements){
            sb.append(o.toString());
        }

        MessageDigest m = MessageDigest.getInstance("SHA");
        byte[] data = sb.toString().getBytes();
        m.update(data, 0, data.length);
        BigInteger i = new BigInteger(1, m.digest());
        return String.format("%1$032X", i);
    }

    public Ticket process(TicketDto ticketDto) throws NoSuchAlgorithmException {
        Ticket productBought = null;
        LocalDateTime lt = LocalDateTime.now();
        //return ticketFactory(ticketDto);
        switch (ticketDto.getTicketType()) {
            case citypass:
                CityPass cityPass = cityPassService.getById(ticketDto.getIdProduct());

                ids = Arrays.asList(String.valueOf(cityPass.getId()),String.valueOf(lt));

                CityPassTicket cityPassTicket = CityPassTicket.builder()
                        .ticketType(ticketDto.getTicketType())
                        .cityPass(cityPass)
                        .quantity(ticketDto.getQuantity())
                        .subTotal(ticketDto.getQuantity() * cityPass.getPrice())
                        .code(generateTicketCode(ids))
                        .build();
                productBought = cityPassTicketRepository.save(cityPassTicket);
                break;

            case theaterplay:
                TheaterPlay theaterPlay = theaterPlayService.getById(ticketDto.getIdProduct());
                ids = Arrays.asList(String.valueOf(theaterPlay.getId()),String.valueOf(lt));

                TheaterPlayTicket theaterPlayTicket = TheaterPlayTicket.builder()
                        .ticketType(ticketDto.getTicketType())
                        .theaterPlay(theaterPlay)
                        .quantity(ticketDto.getQuantity())
                        .subTotal(ticketDto.getQuantity() * theaterPlay.getPrice())
                        .code(generateTicketCode(ids))
                        .build();
                productBought = theaterPlayTicketRepository.save(theaterPlayTicket);
                break;
            /*case excursion:
                Excursion excursion = excursionService.getById(ticketDto.getIdProduct());
                break;*/
        }
        return productBought;
    }


    private Ticket ticketFactory(TicketDto ticketDto){
        Ticket ticket = null;
        switch (ticketDto.getTicketType()) {
            case citypass:
                tickcreateCityPassTicket()
        }
    }*/

    public Ticket getTicketByPurchaseId(Integer idTicket) {
        return ticketRepository.findById(idTicket)
                .orElseThrow(UserNotFoundException::new);
    }

}
