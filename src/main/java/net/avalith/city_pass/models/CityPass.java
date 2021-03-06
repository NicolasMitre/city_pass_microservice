package net.avalith.city_pass.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.dto.CityPassDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="city_pass",
        uniqueConstraints = @UniqueConstraint(name = "name", columnNames= { "name" } )
)

public class CityPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city_pass")
    private Integer idCityPass;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer days;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;

    @NotNull
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;

    public CityPass update(CityPassDto cityPassDto, City city){
        return CityPass.builder()
                .idCityPass(this.idCityPass)
                .name(cityPassDto.getName())
                .description(cityPassDto.getDescription())
                .price(cityPassDto.getPrice())
                .days(cityPassDto.getDays())
                .city(city)
                .isActive(this.isActive)
                .build();
    }
}
