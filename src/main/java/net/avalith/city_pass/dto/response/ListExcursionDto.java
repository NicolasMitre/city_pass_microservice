package net.avalith.city_pass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.dto.ExcursionDto;
import net.avalith.city_pass.models.Excursion;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListExcursionDto {
    private List<ExcursionDto> excursions;

    public static ListExcursionDto fromExcursionsList(List<Excursion> excursionsList){
        return ListExcursionDto.builder()
                .excursions(excursionsList.stream()
                        .map(ExcursionDto::fromExcursion)
                        .collect(Collectors.toList()))
                .build();
    }
}
