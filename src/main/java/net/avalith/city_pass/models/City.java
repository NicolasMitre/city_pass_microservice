package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;

@Data
@Builder
@Entity
public class City {
    private Integer id;
    private String name;
}
