package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.TheaterPlay;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterPlayListDto {
    List<TheaterPlayDto> list;

    public static TheaterPlayListDto fromListTheaterPlay(List<TheaterPlay> theaterPlayList) {
        return TheaterPlayListDto.builder()
                .list(theaterPlayList.stream()
                        .map(TheaterPlayDto::new)
                        .collect(Collectors.toList()))
                .build();
    }

}
