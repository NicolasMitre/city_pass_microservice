package net.avalith.city_pass.models;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
    private Integer id;

    @NotNull
    private Date purchaseDate;

    @NotNull
    private Double totalPrice;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchase")
    private List<Product> list;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
