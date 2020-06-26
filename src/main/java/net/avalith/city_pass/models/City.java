package net.avalith.city_pass.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.dto.CityDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities", uniqueConstraints = @UniqueConstraint(name = "unq_city_name",columnNames = "name"))
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;

    public City update(CityDto cityDto){
        return City.builder()
                .id(this.id)
                .name(cityDto.getName())
                .isActive(this.isActive)
                .build();

    }
}
