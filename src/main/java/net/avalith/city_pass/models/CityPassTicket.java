package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
@Entity
public class CityPassTicket extends Ticket{
    private Integer id;
    private Product product;
    private CityPass cityPass;
}
