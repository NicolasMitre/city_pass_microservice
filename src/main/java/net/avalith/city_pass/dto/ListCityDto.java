package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.City;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListCityDto {
    private List<CityDto> cities;

    public static ListCityDto fromCityList(List<City> cityList){
        return ListCityDto.builder()
                .cities(cityList.stream()
                .map(CityDto::fromCity)
                .collect(Collectors.toList()))
                .build();
    }
}

