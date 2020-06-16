package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.exceptions.CityNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getAllCities(){
        return cityRepository.findAllByIsActive(Boolean.TRUE);
    }

    public City createCity(CityDto cityDto) {
        City city = City.builder()
                .name(cityDto.getName())
                .build();

        return cityRepository.save(city);
    }

    public City getById(Integer idCity){
        return cityRepository.findByIdAndIsActive(idCity,Boolean.TRUE)
                .orElseThrow(CityNotFoundException::new);
    }

    public City getByName(String cityName){
        return cityRepository.findByNameAndIsActive(cityName,Boolean.TRUE)
                .orElseThrow(CityNotFoundException::new);
    }

    public City updateCity(Integer idCity, CityDto cityDto) {
        return cityRepository.findByIdAndIsActive(idCity,Boolean.TRUE)
                .map(city -> update(city, cityDto))
                .map(city -> cityRepository.save(city))
                .orElseThrow(CityNotFoundException::new);
    }

    private City update(City city, CityDto cityDto){
        city.setName(cityDto.getName());
        return city;
    }

    public City deleteCity(Integer idCity) {
        City city = cityRepository.findByIdAndIsActive(idCity, Boolean.TRUE)
                .orElseThrow(CityNotFoundException::new);

        city.setIsActive(Boolean.FALSE);
        return cityRepository.save(city);
    }
}
