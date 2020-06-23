package net.avalith.city_pass.controllers;

import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.dto.ListCityDto;
import net.avalith.city_pass.exceptions.CityNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.services.CityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CityControllerTest {
    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @Before
    public void startUp() {
        initMocks(this);
    }

    @Test
    public void callGetAllCitiesReturnsWithContent(){
        List<City> cities = new ArrayList<>();
        City city1 = City.builder()
                .id(1)
                .name("ciudad1")
                .isActive(Boolean.TRUE)
                .build();
        cities.add(city1);

        when(cityService.getAllCities()).thenReturn(cities);

        ResponseEntity<ListCityDto> test = cityController.getAllCities();

        assertEquals(HttpStatus.OK,test.getStatusCode());
        assertEquals(cities.size(),test.getBody().getCities().size());
    }

    @Test
    public void callGetAllCitiesReturnsNoContent(){
        List<City> cities = Collections.emptyList();

        when(cityService.getAllCities()).thenReturn(cities);

        ResponseEntity<ListCityDto> test = cityController.getAllCities();
        assertEquals(HttpStatus.NO_CONTENT,test.getStatusCode());
    }

    @Test
    public void callGetCityByNameOk(){
        String cityName = "nombre";
        City city = City.builder()
                .id(5)
                .name(cityName)
                .isActive(Boolean.TRUE)
                .build();

        when(cityService.getByNameAndIsActive(cityName)).thenReturn(city);

        ResponseEntity<CityDto> test = cityController.getCityByName(cityName);
        assertEquals(HttpStatus.OK,test.getStatusCode());
        assertEquals(cityName,test.getBody().getName());
    }

    @Test(expected = CityNotFoundException.class)
    public void callGetCityByNameNotFound(){
        String cityName = "nombre";
        City city = City.builder()
                .id(5)
                .name(cityName)
                .isActive(Boolean.TRUE)
                .build();

        when(cityService.getByNameAndIsActive(cityName)).thenReturn(city);

        ResponseEntity<CityDto> test = cityController.getCityByName(cityName);
        assertEquals(HttpStatus.OK,test.getStatusCode());
        assertEquals(cityName,test.getBody().getName());
    }
}