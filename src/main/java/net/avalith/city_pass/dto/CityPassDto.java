package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.CityPass;

import javax.validation.constraints.NotBlank;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityPassDto {
    @NotBlank(message = "Invalid name")
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Integer days;

    @NotBlank
    private Double price;

    public void fromCityPass(CityPass cityPass){
        setName(cityPass.getName());
        setDescription(cityPass.getDescription());
        setDays(cityPass.getDays());
        setPrice(cityPass.getPrice());
    }
}
