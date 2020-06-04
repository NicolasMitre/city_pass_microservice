package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
@Entity
public class TheatherPlay {
    private Integer id;
    private final Integer CAPACITY = 100;
    private City city;
    private Integer duration;
}