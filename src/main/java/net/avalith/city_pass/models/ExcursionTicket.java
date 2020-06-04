package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
@Entity
public class ExcursionTicket extends Ticket{
    private Integer id;
    private Product product;
    private Excursion excursion;
}
