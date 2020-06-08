package net.avalith.city_pass.models;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.List;


@Data
@Builder
@Entity
@Table(name = "city_pass")
public class CityPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private String description;

    @Transient
    private Integer days;
}

