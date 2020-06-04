package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
@Entity
public class Ticket {
    private Integer id;
    private String code;
    private Product product;
}
