package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.Excursion;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcursionDto {
    private Integer id;

    @NotBlank(message = "City name is mandatory")
    private String cityName;

    @NotBlank(message = "Excursion name is mandatory")
    private String name;

    @NotNull
    @Min(value = 0,message = "Minutes must be a positve number")
    private Integer durationInMinutes;

    @NotNull
    @Min(value = 0,message = "Price must be a positve number")
    private Double price;

    @NotBlank(message = "Description is mandatory")
    private String description;

    public static ExcursionDto fromExcursion(Excursion excursion){
        return ExcursionDto.builder()
                .id(excursion.getIdExcursion())
                .name(excursion.getName())
                .cityName(excursion.getCity().getName())
                .description(excursion.getDescription())
                .durationInMinutes(excursion.getDurationInMinutes())
                .price(excursion.getPrice())
                .build();
    }

}
