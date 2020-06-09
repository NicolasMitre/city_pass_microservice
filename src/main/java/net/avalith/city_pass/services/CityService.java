package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.exceptions.CityNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.repositories.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getAllActiveCities(){
        return cityRepository.findAllByStatus(Boolean.TRUE);
    }

    public URI createCity(CityDto cityDto) {
        City city = City.builder()
                .name(cityDto.getName())
                .isActive(true)
                .build();
        city = cityRepository.save(city);
        return getLocation(city);
    }

    private URI getLocation(City city) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(city.getId())
                .toUri();
    }

    public CityDto getById(Integer idCity){
        City city = cityRepository.findByIdAndIsActive(idCity,Boolean.TRUE)
                .orElseThrow(CityNotFoundException::new);

        CityDto cityDto = new CityDto();
        cityDto.fromCity(city);
        return cityDto;
    }

    public City updateCity(Integer idCity, CityDto cityDto) {
        City city = cityRepository.findByIdAndIsActive(idCity,Boolean.TRUE)
                .orElseThrow(CityNotFoundException::new);

        update(city,cityDto);
        city = cityRepository.save(city);
        return City.builder()
                .name(city.getName())
                .isActive(city.getIsActive())
                .build();
    }

    private void update(City city, CityDto cityDto){
        city.setName(cityDto.getName());
    }

    public void deleteCity(Integer idCity) {
        City city = cityRepository.findByIdAndIsActive(idCity, true)
                .orElseThrow(CityNotFoundException::new);
        cityRepository.logicalDelete(city.getId());
    }
}
