package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;


@Data
@Builder
@Entity
@Table(name = "city_pass")
public class CityPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "city")
    private List<Excursion> excursion;
}