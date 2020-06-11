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
        return cityPassRepository.findAllByIsActive(true);
    }

    public CityPass createCityPass(CityPassDto cityPassDto) {

        CityPass cityPass = CityPass.builder()
                .name(cityPassDto.getName())
                .description(cityPassDto.getDescription())
                .days(cityPassDto.getDays())
                .price(cityPassDto.getPrice())
                .isActive(true).build();
        return cityPassRepository.save(cityPass);
    }

    @Deprecated
    private URI getLocation(CityPass cityPass) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cityPass.getId())
                .toUri();
    }

    public CityPass getById(Integer idCityPass) {
        return cityPassRepository.findByIdAndIsActive(idCityPass, true)
                .orElseThrow(CityPassNotFoundException::new);
    }

    public CityPass updateCityPass(Integer idCityPass, CityPassDto cityPassDto) {
        return cityPassRepository.findByIdAndIsActive(idCityPass, Boolean.TRUE)
                .map(cityPass -> update(cityPass, cityPassDto))
                .map(cityPassRepository::save)
                .orElseThrow(CityPassNotFoundException::new);
    }

    private CityPass update(CityPass cityPass, CityPassDto cityPassDto){
        cityPass.setName(cityPassDto.getName());
        cityPass.setDescription(cityPassDto.getDescription());
        cityPass.setPrice(cityPassDto.getPrice());
        cityPass.setDays(cityPassDto.getDays());
        return cityPass;
    }

    public CityPass deleteCityPass(Integer idCityPass) {
        CityPass cityPass = cityPassRepository.findByIdAndIsActive(idCityPass, true)
                .orElseThrow(CityPassNotFoundException::new);
        cityPassRepository.logicDelete(cityPass.getId());
        return cityPass;
    }
}


