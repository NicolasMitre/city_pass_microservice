package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.ExcursionDto;
import net.avalith.city_pass.exceptions.ExcursionNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.Excursion;
import net.avalith.city_pass.repositories.ExcursionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcursionService {
    private final ExcursionRepository excursionRepository;
    private final CityService cityService;

    public ExcursionDto getById(Integer idExcursion) {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion,Boolean.TRUE)
                .orElseThrow(ExcursionNotFoundException::new);

        return ExcursionDto.fromExcursion(excursion);
    }

    public List<ExcursionDto> getAllActiveExcursions() {
        return excursionRepository.findAllByStatus(Boolean.TRUE);
    }

    public void deleteExcursion(Integer idExcursion) {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion, true)
                .orElseThrow(ExcursionNotFoundException::new);
        excursionRepository.logicalDelete(excursion.getId());
    }

    public List<ExcursionDto> getAllActiveExcursionsByCity(String cityName) {
        return cityService.findByNameAndIsActive(cityName).stream()
                .map(ExcursionDto::fromExcursion)
                .collect(Collectors.toList());
    }

    public Excursion updateExcursion(Integer idExcursion, ExcursionDto excursionDto) {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion,Boolean.TRUE)
                .orElseThrow(ExcursionNotFoundException::new);

        City city = cityService.findByNameAndIsActive(excursionDto.getName());

        update(excursion,excursionDto,city);

        excursion = excursionRepository.save(excursion);

        return excursion;
    }

    private Excursion update(Excursion excursion,ExcursionDto excursionDto,City city){
        excursion.setName(excursionDto.getName());
        excursion.setCity(city);
        excursion.setDurationInMinutes(excursionDto.getDurationInMinutes());
        excursion.setPrice(excursionDto.getPrice());
        excursion.setDescription(excursionDto.getDescription());
        return excursion;
    }

    public Excursion createExcursion(ExcursionDto excursionDto) {
        City city = cityService.findByNameAndIsActive(excursionDto.getName());

        Excursion excursion = Excursion.builder()
                .name(excursionDto.getName())
                .description(excursionDto.getDescription())
                .city(city)
                .durationInMinutes(excursionDto.getDurationInMinutes())
                .price(excursionDto.getPrice())
                .build();

        return excursionRepository.save(excursion);
    }
}
