package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import java.util.List;


@Data
@Builder
@Entity
public class CityPass {
    private Integer id;
    private List<Excursion> excursion;
}