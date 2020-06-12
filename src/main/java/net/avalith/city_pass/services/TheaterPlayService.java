package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.TheaterPlayDto;
import net.avalith.city_pass.exceptions.TheaterPlayNotFoundException;
import net.avalith.city_pass.models.TheaterPlay;
import net.avalith.city_pass.repositories.TheaterPlayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TheaterPlayService {
    private final TheaterPlayRepository theaterPlayRepository;
    private final CityService cityService;

    public List<TheaterPlayDto> getAll() {
        return this.theaterPlayRepository.findAllByisActive(Boolean.TRUE)
                .stream()
                .map(TheaterPlayDto::new)
                .collect(Collectors.toList());
    }

    public TheaterPlayDto createTheaterPlay(TheaterPlayDto theaterPlayDto) {
        TheaterPlay theaterPlay = TheaterPlay.builder()
                                .city(findByNameAndIsActive(theaterPlayDto.getCityName(),Boolean.TRUE))
                                .theaterName(theaterPlayDto.getTheaterName())
                                .name(theaterPlayDto.getName())
                                .durationInMinutes(theaterPlayDto.getDurationInMinutes())
                                .price(theaterPlayDto.getPrice())
                                .playDate(theaterPlayDto.getPlayDate())
                                .description(theaterPlayDto.getDescription())
                                .isActive(Boolean.TRUE)
                                .build();
        return new TheaterPlayDto(theaterPlayRepository.save(theaterPlay));
    }

    public TheaterPlayDto getById(Integer id) {
        return theaterPlayRepository.findByIdAndIsActive(id,true)
                .map(TheaterPlayDto::new)
                .orElseThrow(TheaterPlayNotFoundException::new);
    }


    public TheaterPlayDto updateTheaterPlay(Integer id, TheaterPlayDto theaterPlayDto) {
        return this.theaterPlayRepository.findByIdAndIsActive(id, true)
                .map(theaterPlay -> update(theaterPlay,theaterPlayDto))
                .map(theaterPlayRepository::save)
                .map(TheaterPlayDto::new)
                .orElseThrow(TheaterPlayNotFoundException::new);
    }

    private TheaterPlay update(TheaterPlay theaterPlay, TheaterPlayDto theaterPlayDto){
        theaterPlay.setCity(cityService.findByNameAndIsActive(theaterPlayDto.getCityName(),Boolean.TRUE));
        theaterPlay.setName(theaterPlayDto.getName());
        theaterPlay.setDurationInMinutes(theaterPlayDto.getDurationInMinutes());
        theaterPlay.setPrice(theaterPlayDto.getPrice());
        theaterPlay.setPlayDate(theaterPlayDto.getPlayDate());
        theaterPlay.setDescription(theaterPlayDto.getDescription());

        return theaterPlay;
    }


    public TheaterPlayDto logicDelete(Integer id) {
        TheaterPlayDto theaterPlayDto = this.theaterPlayRepository.findByIdAndIsActive(id,Boolean.TRUE)
                .map(TheaterPlayDto::new)
                .orElseThrow(TheaterPlayNotFoundException::new);
        this.theaterPlayRepository.deleteById(id);
        return theaterPlayDto;
    }
}
