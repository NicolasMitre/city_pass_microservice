package net.avalith.city_pass.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.City;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
    @NotBlank(message = "Invalid name")
    private String name;

    public void fromCity(City city){
        setName(city.getName());
    }
}
