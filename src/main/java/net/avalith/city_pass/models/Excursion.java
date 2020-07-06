package net.avalith.city_pass.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.dto.ExcursionDto;

import javax.persistence.Column;
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
@AllArgsConstructor
@NoArgsConstructor
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExcursion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private Integer durationInMinutes;

    @NotNull
    private Double price;

    @NotNull
    private String description;

    @NotNull
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;

    public Excursion update(ExcursionDto excursionDto, City city){
        return Excursion.builder()
                .idExcursion(this.idExcursion)
                .city(city)
                .name(excursionDto.getName())
                .durationInMinutes(excursionDto.getDurationInMinutes())
                .price(excursionDto.getPrice())
                .description(excursionDto.getDescription())
                .isActive(this.isActive)
                .build();
    }
}