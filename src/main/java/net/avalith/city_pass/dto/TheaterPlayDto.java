package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.TheaterPlay;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterPlayDto {
    @NotNull
    private String name;

    @NotNull
    private Integer durationInMinutes;

    @NotNull
    private double price;

    @NotNull
    private LocalDate playDate;

    @NotNull
    private String description;

    public TheaterPlayDto (TheaterPlay theaterPlay)
    {
        this.name = theaterPlay.getName();
        this.durationInMinutes = theaterPlay.getDurationInMinutes();
        this.price = theaterPlay.getPrice();
        this.playDate = theaterPlay.getPlayDate();
        this.description = theaterPlay.getDescription();
    }

}
