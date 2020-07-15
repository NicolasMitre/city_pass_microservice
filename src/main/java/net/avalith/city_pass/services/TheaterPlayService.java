package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.TheaterPlayDto;
import net.avalith.city_pass.exceptions.TheaterPlayNotFoundException;
import net.avalith.city_pass.models.TheaterPlay;
import net.avalith.city_pass.repositories.TheaterPlayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TheaterPlayService {
    private final TheaterPlayRepository theaterPlayRepository;
    private final CityService cityService;

    public List<TheaterPlay> getAll() {
        return this.theaterPlayRepository.findAllByIsActive(Boolean.TRUE);
    }

    public TheaterPlay createTheaterPlay(TheaterPlayDto theaterPlayDto) {
        TheaterPlay theaterPlay = TheaterPlay.builder()
                                .city(cityService.getByName(theaterPlayDto.getCityName()))
                                .theaterName(theaterPlayDto.getTheaterName())
                                .name(theaterPlayDto.getName())
                                .durationInMinutes(theaterPlayDto.getDurationInMinutes())
                                .price(theaterPlayDto.getPrice())
                                .playDate(theaterPlayDto.getPlayDate())
                                .description(theaterPlayDto.getDescription())
                                .build();
        return theaterPlayRepository.save(theaterPlay);
    }

    public TheaterPlay getById(Integer id) {
        return theaterPlayRepository.findByIdTheaterPlayAndIsActive(id,Boolean.TRUE)
                .orElseThrow(TheaterPlayNotFoundException::new);
    }


    public TheaterPlay updateTheaterPlay(Integer id, TheaterPlayDto theaterPlayDto) {
        return this.theaterPlayRepository.findByIdTheaterPlayAndIsActive(id, Boolean.TRUE)
                .map(theaterPlay -> update(theaterPlay,theaterPlayDto))
                .map(theaterPlayRepository::save)
                .orElseThrow(TheaterPlayNotFoundException::new);
    }

    private TheaterPlay update(TheaterPlay theaterPlay, TheaterPlayDto theaterPlayDto){
        theaterPlay.setCity(cityService.getByName(theaterPlayDto.getCityName()));
        theaterPlay.setName(theaterPlayDto.getName());
        theaterPlay.setDurationInMinutes(theaterPlayDto.getDurationInMinutes());
        theaterPlay.setPrice(theaterPlayDto.getPrice());
        theaterPlay.setPlayDate(theaterPlayDto.getPlayDate());
        theaterPlay.setDescription(theaterPlayDto.getDescription());

        return theaterPlay;
    }


    public TheaterPlay logicDelete(Integer id) {
        TheaterPlay theaterPlay = this.theaterPlayRepository.findByIdTheaterPlayAndIsActive(id,Boolean.TRUE)
                .orElseThrow(TheaterPlayNotFoundException::new);
        theaterPlay.setIsActive(Boolean.FALSE);
        return this.theaterPlayRepository.save(theaterPlay);

    }

    public TheaterPlay getByName(String theaterPlayName) {
       return this.theaterPlayRepository.findByNameAndIsActive(theaterPlayName,Boolean.TRUE)
                .orElseThrow(TheaterPlayNotFoundException::new);
    }

    public List<TheaterPlay> getByCityName(String cityName) {
        return this.theaterPlayRepository.findByCityNameAndIsActive(cityName,Boolean.TRUE);
    }
}
