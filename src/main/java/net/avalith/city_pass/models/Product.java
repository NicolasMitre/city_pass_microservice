package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Builder
@Entity
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer duration;
    private Double discount;
    private String description;
}