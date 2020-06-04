package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Builder
@Entity
public class Excursion {
    private Integer id;
    private City city;
    private Integer duration;
    private Date schendule;
    private Integer capacity;
}