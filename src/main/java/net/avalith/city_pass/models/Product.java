package net.avalith.city_pass.models;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
    private Integer id;

    @NotNull
    private Double price;

    @NotNull
    private Integer duration;

    @NotNull
    private Double discount;

    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

}