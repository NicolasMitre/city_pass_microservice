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

    public List<City> getAll(){
        return cityRepository.findAll();
    }

    public URI createCity(CityDto cityDto) {
        City city = City.builder().name(cityDto.getName()).build();
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
        City city = cityRepository.findById(idCity)
                .orElseThrow(CityNotFoundException::new);

        CityDto cityDto = new CityDto();
        cityDto.fromCity(city);
        return cityDto;
    }
}
