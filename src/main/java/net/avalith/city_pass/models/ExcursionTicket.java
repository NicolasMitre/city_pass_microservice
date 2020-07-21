package net.avalith.city_pass.models;

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
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=true)
@PrimaryKeyJoinColumn(name="id_ticket")
public class ExcursionTicket extends Ticket{
    @ManyToOne
    @JoinColumn(name = "id_excursion")
    private Excursion excursion;

    @NotNull
    @Column(unique = true)
    private String code;

    public static ExcursionTicket createExcursionTicket(TicketType ticketType, Excursion excursion, Integer quantity,
                                                        Double subTotal, String code, Purchase purchase) {
        return ExcursionTicket.builder()
                .ticketType(ticketType)
                .excursion(excursion)
                .unitPrice(excursion.getPrice())
                .quantity(quantity)
                .subTotal(subTotal)
                .code(code)
                .purchasedDate(purchase.getPurchaseDate())
                .purchase(purchase)
                .build();
    }
}