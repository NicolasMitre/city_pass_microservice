package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.services.CityService;
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
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    @GetMapping("")
    public ResponseEntity<List<City>> getAllCities(){
        List<City> list = cityService.getAllActiveCities();
        return (list.size() >0 )? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idCity}")
    public ResponseEntity<City> getCityById(@PathVariable(name = "idCity") Integer idCity){
        return ResponseEntity.ok(cityService.getById(idCity));
    }

    @PostMapping("")
    public ResponseEntity<City> createCity(@Valid @RequestBody CityDto cityDto ){
        City city = cityService.createCity(cityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(city);
    }

    @PutMapping("/{idCity}")
    public ResponseEntity<City> updateCity(@PathVariable(name = "idCity")Integer idCity, @Valid @RequestBody CityDto cityDto){
        return ResponseEntity.ok(cityService.updateCity(idCity,cityDto));
    }

    @DeleteMapping("/{idCity}")
    public ResponseEntity<City> deleteCity(@PathVariable(name = "idCity")Integer idCity){
        return ResponseEntity.ok().body(cityService.deleteCity(idCity));
    }

}
