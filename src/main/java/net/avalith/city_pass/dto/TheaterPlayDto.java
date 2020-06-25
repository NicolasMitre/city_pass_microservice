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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterPlayDto {
    private Integer id;

    @NotBlank
    private String cityName;

    @NotBlank
    private String theaterName;

    @NotBlank
    private String name;

    @Min(value = 1,message = "Minimum Value Should be 1")
    private Integer durationInMinutes;

    @Min(value = 1,message = "Minimum Value Should be 1")
    private double price;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate playDate;

    @NotNull
    private String description;

    public TheaterPlayDto (TheaterPlay theaterPlay)
    {
        this.id = theaterPlay.getId();
        this.cityName = theaterPlay.getCity().getName();
        this.theaterName = theaterPlay.getTheaterName();
        this.name = theaterPlay.getName();
        this.durationInMinutes = theaterPlay.getDurationInMinutes();
        this.price = theaterPlay.getPrice();
        this.playDate = theaterPlay.getPlayDate();
        this.description = theaterPlay.getDescription();
    }

}
