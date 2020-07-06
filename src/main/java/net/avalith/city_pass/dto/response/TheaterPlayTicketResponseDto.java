package net.avalith.city_pass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.avalith.city_pass.models.TheaterPlay;
import net.avalith.city_pass.models.TheaterPlayTicket;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterPlayTicketResponseDto extends TicketResponseDto{
    private String theaterName;
    private String theaterPlayName;
    private LocalDate playDate;

    public static TheaterPlayTicketResponseDto createTheaterPlayTicketResponseDto(TheaterPlayTicket theaterPlayTicket){
        TheaterPlay theaterPlay = theaterPlayTicket.getTheaterPlay();

        return TheaterPlayTicketResponseDto.builder()
                .productType(theaterPlayTicket.getTicketType().name())
                .cityName(theaterPlay.getCity().getName())
                .quantity(theaterPlayTicket.getQuantity())
                .unitPrice(theaterPlayTicket.getUnitPrice())
                .subTotal(theaterPlayTicket.getSubTotal())
                .code(theaterPlayTicket.getCode())
                .theaterName(theaterPlay.getTheaterName())
                .theaterPlayName(theaterPlay.getName())
                .playDate(theaterPlay.getPlayDate())
                .build();
    }
}

