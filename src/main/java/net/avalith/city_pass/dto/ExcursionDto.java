package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.Excursion;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcursionDto {

    private Integer id;

    @NotBlank
    private String cityName;

    @NotBlank
    private String name;

    @Min(0)
    private Integer durationInMinutes;

    @Min(0)
    private Double price;

    @NotBlank
    private String description;

    public static ExcursionDto fromExcursion(Excursion excursion){
        return ExcursionDto.builder()
                .id(excursion.getId())
                .name(excursion.getName())
                .cityName(excursion.getCity().getName())
                .description(excursion.getDescription())
                .durationInMinutes(excursion.getDurationInMinutes())
                .price(excursion.getPrice())
                .build();
    }

}
