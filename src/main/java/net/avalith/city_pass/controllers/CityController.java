package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @GetMapping("")
    public ResponseEntity<List<City>> getAllCities(){
        List<City> list = cityService.getAll();
        return (list.size() >0 )? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idCity}")
    public ResponseEntity<CityDto> getCityById(@PathVariable(name = "idCity") Integer idCity){
        return ResponseEntity.ok(cityService.getById(idCity));
    }

    @PostMapping("")
    public ResponseEntity<?> createCity(@Valid @RequestBody CityDto cityDto ){
        URI uri = cityService.createCity(cityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(uri);
    }


}
