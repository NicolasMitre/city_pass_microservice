package net.avalith.city_pass.models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper=true)
@Table(uniqueConstraints =
        @UniqueConstraint(columnNames = {"product_id"}))
public class ExcursionTicket extends Ticket{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "excursion_id")
    private Excursion excursion;
    @NotNull
    private String code;
}