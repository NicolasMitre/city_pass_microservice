package net.avalith.city_pass.models;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
public class TheatherPlay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
    private Integer id;
    private final Integer CAPACITY = 100;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cities_id")
    private City city;

    @NotNull
    private String name;

    @NotNull
    private Integer duration;
}