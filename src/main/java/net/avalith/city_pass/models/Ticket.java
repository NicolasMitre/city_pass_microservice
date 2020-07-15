package net.avalith.city_pass.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.avalith.city_pass.models.enums.TicketType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idTicket;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @NotNull
    private Double unitPrice;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double subTotal;

    @ManyToOne
    @JoinColumn(name = "id_purchase")
    @JsonBackReference
    private Purchase purchase;

    @NotNull
    private LocalDateTime purchasedDate;
}

