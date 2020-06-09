package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityPassDto;
import net.avalith.city_pass.exceptions.CityPassNotFoundException;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.repositories.CityPassRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityPassService {

    private final CityPassRepository cityPassRepository;

    public List<CityPass> getAllCityPassesActives() {
        return cityPassRepository.findAllByStatus(true);
    }

    public URI createCityPass(CityPassDto cityPassDto) {
        CityPass cityPass = CityPass.builder().name(cityPassDto.getName()).build();
        cityPass = cityPassRepository.save(cityPass);
        return getLocation(cityPass);
    }

    private URI getLocation(CityPass cityPass) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cityPass.getId())
                .toUri();
    }

    public CityPassDto getById(Integer idCityPass) {
        CityPass cityPass = cityPassRepository.findByIdAndIsActive(idCityPass, true)
                .orElseThrow(CityPassNotFoundException::new);
        CityPassDto cityPassDto = new CityPassDto();
        cityPassDto.fromCityPass(cityPass);
        return cityPassDto;
    }

    public CityPass updateCityPass(Integer idCityPass, CityPassDto cityPassDto) {
        CityPass cityPass = cityPassRepository.findByIdAndIsActive(idCityPass, true)
                .orElseThrow(CityPassNotFoundException::new);
        update(cityPass, cityPassDto);
        cityPass = cityPassRepository.save(cityPass);
        return CityPass.builder()
                .name(cityPass.getName())
                .description(cityPass.getDescription())
                .price(cityPass.getPrice())
                .days(cityPass.getDays())
                .build();

    }

    private void update(CityPass cityPass, CityPassDto cityPassDto){
        cityPass.setName(cityPassDto.getName());
        cityPass.setDescription(cityPassDto.getDescription());
        cityPass.setPrice(cityPassDto.getPrice());
        cityPass.setDays(cityPassDto.getDays());
    }

    public void deleteCityPass(Integer idCityPass) {
        CityPass cityPass = cityPassRepository.findByIdAndIsActive(idCityPass, true)
                .orElseThrow(CityPassNotFoundException::new);
        cityPassRepository.logicDelete(idCityPass);
    }
}


