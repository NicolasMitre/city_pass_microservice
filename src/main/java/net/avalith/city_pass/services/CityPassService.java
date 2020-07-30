package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityPassDto;
import net.avalith.city_pass.exceptions.CityPassNameIsAlreadyUsedException;
import net.avalith.city_pass.exceptions.CityPassNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.repositories.CityPassRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityPassService {

    private final CityPassRepository cityPassRepository;
    private final CityService cityService;

    public List<CityPass> getAllCityPasses() {
        return cityPassRepository.findAllByIsActive(true);
    }

    public CityPass createCityPass(CityPassDto cityPassDto) {
        CityPass cityPass = CityPass.builder()
                .name(cityPassDto.getName())
                .description(cityPassDto.getDescription())
                .days(cityPassDto.getDays())
                .price(cityPassDto.getPrice())
                .city(cityService.getByName(cityPassDto.getCityName()))
                .build();

        try {
            cityPass = cityPassRepository.save(cityPass);
        } catch (DataIntegrityViolationException e) {
            throw new CityPassNameIsAlreadyUsedException();
        }

        return cityPass;
    }

    public CityPass getById(Integer idCityPass) {
        return cityPassRepository.findByIdCityPassAndIsActive(idCityPass, true)
                .orElseThrow(CityPassNotFoundException::new);
    }

    public CityPass updateCityPass(Integer idCityPass, CityPassDto cityPassDto) {
        City city = cityService.getByName(cityPassDto.getCityName());

        CityPass cityPass = cityPassRepository.findByIdCityPassAndIsActive(idCityPass, Boolean.TRUE)
                .orElseThrow(CityPassNotFoundException::new);

        try {
            cityPass = cityPass.update(cityPassDto, city);
            cityPass = cityPassRepository.save(cityPass);
        } catch (DataIntegrityViolationException e) {
            throw new CityPassNameIsAlreadyUsedException();
        }

        return cityPass;
    }

    public CityPass deleteCityPass(Integer idCityPass) {
        CityPass cityPass = cityPassRepository.findByIdCityPassAndIsActive(idCityPass, true)
                .orElseThrow(CityPassNotFoundException::new);
        cityPass.setIsActive(Boolean.FALSE);

        return cityPassRepository.save(cityPass);
    }
}


