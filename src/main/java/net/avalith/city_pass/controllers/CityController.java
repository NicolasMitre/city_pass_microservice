package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.dto.ListCityDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    @GetMapping("")
    public ResponseEntity<ListCityDto> getAllCities(){
        List<City> cities = cityService.getAllCities();
        
        return (cities.size() > 0 )? ResponseEntity.ok(ListCityDto.fromCityList(cities)) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "", params="cityName")
    public ResponseEntity<CityDto> getCityByName(@RequestParam(value = "cityName") String cityName){
        City city = cityService.getByName(cityName);
        return ResponseEntity.ok(CityDto.fromCity(city));
    }

    @GetMapping("/{idCity}")
    public ResponseEntity<CityDto> getCityById(@PathVariable(name = "idCity") Integer idCity){
        City city = cityService.getById(idCity);
        return ResponseEntity.ok(CityDto.fromCity(city));
    }

    @PostMapping("")
    public ResponseEntity<CityDto> createCity(@Valid @RequestBody CityDto cityDto ){
        City city = cityService.createCity(cityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CityDto.fromCity(city));
    }

    @PutMapping("/{idCity}")
    public ResponseEntity<CityDto> updateCity(@PathVariable(name = "idCity")Integer idCity, @Valid @RequestBody CityDto cityDto){
        City city = cityService.updateCity(idCity,cityDto);
        return ResponseEntity.ok(CityDto.fromCity(city));
    }

    @DeleteMapping("/{idCity}")
    public ResponseEntity<CityDto> deleteCity(@PathVariable(name = "idCity")Integer idCity){
        City city = cityService.deleteCity(idCity);
        return ResponseEntity.ok().body(CityDto.fromCity(city));
    }

}
