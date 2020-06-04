package net.avalith.city_pass.models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper=true)
public class TheatherPlayTicket extends Ticket{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
    private Integer id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private TheatherPlay theatherPlay;
    @NotNull
    private String code;
}
