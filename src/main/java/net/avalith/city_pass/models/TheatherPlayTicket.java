package net.avalith.city_pass.models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class TheatherPlayTicket extends Ticket{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "theather_play_id")
    private TheatherPlay theatherPlay;

    @NotNull
    @Column(unique = true)
    private String code;
}
