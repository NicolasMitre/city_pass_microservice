package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.ExcursionDto;
import net.avalith.city_pass.exceptions.ExcursionNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.Excursion;
import net.avalith.city_pass.repositories.ExcursionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExcursionService {
    private final ExcursionRepository excursionRepository;
    private final CityService cityService;

    public List<Excursion> getAllActiveExcursions() {
        List<Excursion> list = excursionRepository.findAllByStatus(Boolean.TRUE);
        return list;
    }

    public List<Excursion> getAllActiveExcursionsByCity(String cityName) {
        City city = cityService.getByName(cityName);

        return excursionRepository.findByCityNameAndStatus(city.getName(),Boolean.TRUE);
    }

    public Excursion getById(Integer idExcursion) throws ExcursionNotFoundException {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion,Boolean.TRUE);

        return Optional.ofNullable(excursion).orElseThrow(ExcursionNotFoundException::new);
    }

    public Excursion getByNameActive(String excursionName) throws ExcursionNotFoundException {
        Excursion excursion = getByName(excursionName,Boolean.TRUE);

        return Optional.ofNullable(excursion).orElseThrow(ExcursionNotFoundException::new);
    }


    public Excursion deleteExcursion(Integer idExcursion) throws ExcursionNotFoundException {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion, true);

        Optional.ofNullable(excursion).orElseThrow(ExcursionNotFoundException::new);

        excursion.setIsActive(Boolean.FALSE);
        return excursionRepository.save(excursion);
    }


    public Excursion updateExcursion(Integer idExcursion, ExcursionDto excursionDto) throws ExcursionNotFoundException {
        Excursion excursion = excursionRepository.findByIdAndIsActive(idExcursion,Boolean.TRUE);

        Optional.ofNullable(excursion).orElseThrow(ExcursionNotFoundException::new);

        City city = cityService.getByName(excursionDto.getCityName());

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
        City city = cityService.getByName(excursionDto.getCityName());

        Excursion excursion = getByName(excursionDto.getName(),Boolean.FALSE);

        if(excursion == null){
            excursion = Excursion.builder()
                    .name(excursionDto.getName())
                    .description(excursionDto.getDescription())
                    .city(city)
                    .durationInMinutes(excursionDto.getDurationInMinutes())
                    .price(excursionDto.getPrice())
                    .build();
        } else{
            excursion.setIsActive(true);
            update(excursion,excursionDto,city);
        }
        return excursionRepository.save(excursion);
    }

    private Excursion getByName(String name,Boolean status){
        return excursionRepository.findByNameAndIsActive(name,status);
    }

}
