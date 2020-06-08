package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.repositories.CityPassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityPassService {

    private final CityPassRepository cityPassRepository;

    public List<CityPass> getAllCityPasses() {
        return cityPassRepository.findAll();
    }
}


