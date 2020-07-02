package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.ExcursionDto;
import net.avalith.city_pass.exceptions.ExcursionNameAlreadyUsedException;
import net.avalith.city_pass.exceptions.ExcursionNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.Excursion;
import net.avalith.city_pass.repositories.ExcursionRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExcursionService {
    private final ExcursionRepository excursionRepository;
    private final CityService cityService;

    public List<Excursion> getAllActiveExcursions() {
        return excursionRepository.findAllByIsActive(Boolean.TRUE);
    }

    public List<Excursion> getAllActiveExcursionsByCity(String cityName) {
        City city = cityService.getByName(cityName);

        return excursionRepository.findByCityNameAndStatus(city.getName(), Boolean.TRUE);
    }

    public Excursion createExcursion(ExcursionDto excursionDto) throws ExcursionNameAlreadyUsedException {
        City city = cityService.getByName(excursionDto.getCityName());
        Excursion excursion = null;
        Optional<Excursion> optExcursion = excursionRepository.findByNameAndIsActive(excursionDto.getName(), Boolean.FALSE);

        if (!optExcursion.isPresent()) {
            excursion = Excursion.builder()
                    .name(excursionDto.getName())
                    .description(excursionDto.getDescription())
                    .city(city)
                    .durationInMinutes(excursionDto.getDurationInMinutes())
                    .price(excursionDto.getPrice())
                    .build();
        } else {
            excursion = optExcursion.get();
            excursion.setIsActive(Boolean.TRUE);
            excursion = excursion.update(excursionDto, city);
        }
        try {
            excursion = excursionRepository.save(excursion);
        } catch (DataIntegrityViolationException e) {
            throw new ExcursionNameAlreadyUsedException();
        }
        return excursion;
    }

    public Excursion getById(Integer idExcursion) throws ExcursionNotFoundException {
        return excursionRepository.findByIdAndIsActive(idExcursion, Boolean.TRUE)
                .orElseThrow(ExcursionNotFoundException::new);
    }

    public Excursion getByNameActive(String excursionName) throws ExcursionNotFoundException {
        return excursionRepository.findByNameAndIsActive(excursionName, Boolean.TRUE)
                .orElseThrow(ExcursionNotFoundException::new);
    }

    public Excursion deleteExcursion(Integer idExcursion) throws ExcursionNotFoundException {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion, Boolean.TRUE)
                .orElseThrow(ExcursionNotFoundException::new);

        excursion.setIsActive(Boolean.FALSE);
        return excursionRepository.save(excursion);
    }

    public Excursion updateExcursion(Integer idExcursion, ExcursionDto excursionDto) throws ExcursionNotFoundException, ExcursionNameAlreadyUsedException {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion, Boolean.TRUE)
                .orElseThrow(ExcursionNotFoundException::new);

        City city = cityService.getByName(excursionDto.getCityName());
        excursion = excursion.update(excursionDto, city);

        try {
            excursion = excursionRepository.save(excursion);
        } catch (DataIntegrityViolationException e) {
            throw new ExcursionNameAlreadyUsedException();
        }
        return excursion;
    }
}
