package net.avalith.city_pass.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.exceptions.CityNameAlreadyUsedException;
import net.avalith.city_pass.exceptions.CityNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.repositories.CityRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static net.avalith.city_pass.utils.Constants.CITY_NAME_ALREADY_USED_MESSAGE;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAllByIsActive(Boolean.TRUE);
    }

    public City createCity(CityDto cityDto) throws CityNameAlreadyUsedException {
        City city = City.builder()
                .name(cityDto.getName())
                .build();
        try {
            city = cityRepository.save(city);
        } catch (DataIntegrityViolationException e) {
            throw new CityNameAlreadyUsedException(CITY_NAME_ALREADY_USED_MESSAGE);
        }
        return city;
    }

    private City getByName(String cityName) {
        return cityRepository.findByName(cityName);
    }

    public City getById(Integer idCity) {
        return cityRepository.findByIdAndIsActive(idCity, Boolean.TRUE)
                .orElseThrow(CityNotFoundException::new);
    }

    public City getByNameAndIsActive(String cityName) {
        return cityRepository.findByNameAndIsActive(cityName, Boolean.TRUE)
                .orElseThrow(CityNotFoundException::new);
    }

    public City updateCity(Integer idCity, CityDto cityDto) throws CityNameAlreadyUsedException {
        City city = getById(idCity);
        city = city.update(cityDto);
        try {
            city = cityRepository.save(city);
        } catch (DataIntegrityViolationException e) {
            throw new CityNameAlreadyUsedException(CITY_NAME_ALREADY_USED_MESSAGE);
        }
        return city;
    }

    public City deleteCity(Integer idCity) {
        City city = getById(idCity);
        city.setIsActive(Boolean.FALSE);
        return cityRepository.save(city);
    }
}
