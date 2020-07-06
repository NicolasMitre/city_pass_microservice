package net.avalith.city_pass.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TheaterPlay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_theaterplay")
    private Integer idTheaterPlay;

    private final Integer CAPACITY = 100;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;

    @NotNull
    private String theaterName;

    @NotNull
    private String name;

    @NotNull
    private Integer durationInMinutes;

    @NotNull
    private Double price;

    @NotNull
    private LocalDate playDate;

    @NotNull
    private String description;

    @Builder.Default
    @NotNull
    private Boolean isActive = Boolean.TRUE;
}