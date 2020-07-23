package net.avalith.city_pass.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.avalith.city_pass.models.enums.PurchaseStatus;
import net.avalith.city_pass.models.enums.TicketType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=true)
@PrimaryKeyJoinColumn(name="id_ticket")
public class CityPassTicket extends Ticket{
    @ManyToOne
    @JoinColumn(name = "id_city_pass")
    private CityPass cityPass;

    @NotNull
    @Column(unique = true)
    private String code;

    public static CityPassTicket createCityPassTicket(TicketType ticketType,CityPass cityPass, Integer quantity,
                                                      Double subtotal,String code, LocalDateTime purchasedDate){
        return CityPassTicket.builder()
                .ticketType(ticketType)
                .cityPass(cityPass)
                .unitPrice(cityPass.getPrice())
                .quantity(quantity)
                .subTotal(subtotal)
                .code(code)
                .purchasedDate(purchasedDate)
                .ticketStatus(PurchaseStatus.pending)
                .build();
    }

    @Override
    public String getName() {
        return cityPass.getName();
    }
}
