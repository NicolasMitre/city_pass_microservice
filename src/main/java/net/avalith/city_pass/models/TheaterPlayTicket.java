package net.avalith.city_pass.models;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.avalith.city_pass.models.enums.TicketType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=true)
@PrimaryKeyJoinColumn(name="id_ticket")
public class TheaterPlayTicket extends Ticket{
    @ManyToOne
    @JoinColumn(name = "id_theaterplay")
    private TheaterPlay theaterPlay;

    @NotNull
    @Column(unique = true)
    private String code;

    public static TheaterPlayTicket createTheaterPlayTicket(TicketType ticketType, TheaterPlay theaterPlay, Integer quantity,
                                                            Double subTotal, String code, Purchase purchase) {
        return TheaterPlayTicket.builder()
                .ticketType(ticketType)
                .theaterPlay(theaterPlay)
                .unitPrice(theaterPlay.getPrice())
                .quantity(quantity)
                .subTotal(subTotal)
                .code(code)
                .purchasedDate(purchase.getPurchaseDate())
                .purchase(purchase)
                .build();
    }
}
