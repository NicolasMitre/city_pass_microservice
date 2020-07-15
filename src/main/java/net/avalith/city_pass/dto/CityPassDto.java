package net.avalith.city_pass.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.CityPass;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityPassDto {
    private Integer id;

    @NotBlank(message = "Invalid name")
    private String name;

    @NotBlank
    private String description;

    @Min(value = 1)
    private Integer days;

    @Min(value = 0)
    private Double price;

    @NotBlank
    private String cityName;

    public static CityPassDto fromCityPass(CityPass cityPass){
        return CityPassDto.builder()
                .name(cityPass.getName())
                .description(cityPass.getDescription())
                .days(cityPass.getDays())
                .price(cityPass.getPrice())
                .cityName(cityPass.getCity().getName())
                .build();
        }
}
