package net.avalith.city_pass.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


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
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer days;

    @NotNull
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;
}