package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
public class Purchase {
    private Integer id;
    private Date purchaseDate;
    private List<Product> list;

}
