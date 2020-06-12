package net.avalith.city_pass.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
    private String cityName;

    @NotNull
    private String theaterName;

    @NotNull
    private String name;

    @NotNull
    private Integer durationInMinutes;

    @NotNull
    private double price;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate playDate;

    @NotNull
    private String description;

    public TheaterPlayDto (TheaterPlay theaterPlay)
    {
        this.cityName = theaterPlay.getCity().getName();
        this.name = theaterPlay.getName();
        this.durationInMinutes = theaterPlay.getDurationInMinutes();
        this.price = theaterPlay.getPrice();
        this.playDate = theaterPlay.getPlayDate();
        this.description = theaterPlay.getDescription();
    }

}
