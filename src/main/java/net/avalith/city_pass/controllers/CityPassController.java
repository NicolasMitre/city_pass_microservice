package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityPassDto;
import net.avalith.city_pass.dto.RoleDto;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.services.CityPassService;
import net.avalith.city_pass.services.CityService;
import net.avalith.city_pass.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city_pass")
public class CityPassController {
    private final CityPassService cityPassService;

    @GetMapping("")
    public ResponseEntity<CityPassDto> getAllCityPasses(){
        CityPassDto cityPassDto = cityPassService.getAllCityPassesActives();
        return ResponseEntity.ok(cityPassDto);
    }

    @GetMapping("/{idCityPass}")
    public ResponseEntity<CityPass> getCityPassById(@PathVariable(name = "idCityPass") Integer idCityPass){
        CityPass cityPass = cityPassService.getById(idCityPass);
        return ResponseEntity.ok(cityPass);
    }

    @PostMapping("")
    public ResponseEntity<CityPass> createCityPass(@Valid @RequestBody CityPassDto cityPassDto){
        CityPass cityPass = cityPassService.createCityPass(cityPassDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cityPass);
    }

    @PutMapping("/{idCityPass}")
    public ResponseEntity<CityPass> modifyCityPassById(@PathVariable(name = "idCityPass") Integer idCityPass,
                                                @Valid @RequestBody CityPassDto cityPassDto) {
        CityPass cityPass = cityPassService.updateCityPass(idCityPass, cityPassDto);
        return ResponseEntity.ok(cityPass);
    }

    @DeleteMapping("/{idCityPass}")
    public ResponseEntity<CityPass> deleteCityPassById(@PathVariable(name = "idCityPass") Integer idCityPass){
        return ResponseEntity.ok(cityPassService.deleteCityPass(idCityPass));
    }
}

