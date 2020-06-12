package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.CityPass;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCityPassDto {

    private List<CityPassDto> cityPassesDto;

    public static ListCityPassDto fromListDto (List<CityPass> list){
        return ListCityPassDto.builder()
                .cityPassesDto(list.stream()
                        .map(CityPassDto::fromCityPass)
                        .collect(Collectors.toList()))
                .build();
    }
}
