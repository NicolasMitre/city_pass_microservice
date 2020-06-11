package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.dto.ExcursionDto;
import net.avalith.city_pass.exceptions.ExcursionNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.Excursion;
import net.avalith.city_pass.repositories.CityRepository;
import net.avalith.city_pass.repositories.ExcursionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcursionService {
    private final ExcursionRepository excursionRepository;
    private final CityRepository cityRepository;

    public ExcursionDto getById(Integer idExcursion) {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion,Boolean.TRUE)
                .orElseThrow(ExcursionNotFoundException::new);

        ExcursionDto excursionDto = new ExcursionDto();
        excursionDto.fromExcursion(excursion);
        return excursionDto;
    }

    public List<ExcursionDto> getAllActiveExcursions() {
        return excursionRepository.findAllByStatus(Boolean.TRUE);
    }

    public void deleteExcursion(Integer idExcursion) {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion, true)
                .orElseThrow(ExcursionNotFoundException::new);
        excursionRepository.logicalDelete(excursion.getId());
    }



    public List<ExcursionDto> getAllActiveExcursionsByCity(Integer idCity) {
        return void;
    }


    public Excursion updateExcursion(Integer idExcursion, ExcursionDto excursionDto) {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion,Boolean.TRUE)
                .orElseThrow(ExcursionNotFoundException::new);
        City city = con el nombre que tiene el dto de Excursione

        update(excursion,excursionDto,city);

        excursion = excursionRepository.save(excursion);

        return excursion;
    }

    private void update(Excursion excursion,ExcursionDto excursionDto,City city){
        excursion.setName(excursionDto.getName());
        excursion.setCity(city);
        excursion.setDurationInMinutes(excursionDto.getDurationInMinutes());
        excursion.setPrice(excursionDto.getPrice());
        excursion.setDescription(excursionDto.getDescription());
    }

    //TODO finalizar
    public URI createExcursion(ExcursionDto excursionDto) {
        City city = con el nombre que tiene el dto de Excursione

        Excursion excursion = Excursion.builder()
                .name(excursionDto.getName())
                .description(excursionDto.getDescription())
                .city(city)
                .durationInMinutes(excursionDto.getDurationInMinutes())
                .price(excursionDto.getPrice())
                .build();

        excursion = excursionRepository.save(excursion);
        return getLocation(excursion);
    }

    private URI getLocation(Excursion excursion) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(excursion.getId())
                .toUri();
    }




}
