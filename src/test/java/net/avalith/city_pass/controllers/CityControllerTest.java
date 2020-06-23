package net.avalith.city_pass.controllers;

import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.dto.ListCityDto;
import net.avalith.city_pass.exceptions.CityNameAlreadyUsedException;
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
        when(cityService.getByNameAndIsActive(cityName)).thenThrow(new CityNotFoundException());

        cityController.getCityByName(cityName);
    }

    @Test(expected = CityNotFoundException.class)
    public void callGetCityByIdWithInvalidId(){
        Integer id = 5;
        when(cityService.getById(id)).thenThrow(new CityNotFoundException());
        cityController.getCityById(id);
    }

    @Test
    public void callGetCityByIdOk(){
        Integer id = 5;
        City city = City.builder()
                .id(5)
                .name("nombre")
                .isActive(Boolean.TRUE)
                .build();

        when(cityService.getById(id)).thenReturn(city);
        ResponseEntity<CityDto> response = cityController.getCityById(id);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(city.getName(),response.getBody().getName());
        assertEquals(city.getId(),response.getBody().getId());
    }

    @Test
    public void callCreateCityOk() throws CityNameAlreadyUsedException {
        Integer id = 5;
        CityDto cityDto = CityDto.builder()
                .name("test")
                .build();

        City city = City.builder()
                .id(id)
                .name(cityDto.getName())
                .isActive(Boolean.TRUE)
                .build();

        when(cityService.createCity(cityDto)).thenReturn(city);
        ResponseEntity<CityDto> response = cityController.createCity(cityDto);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(cityDto.getName(),response.getBody().getName());
        assertEquals(city.getId(),response.getBody().getId());
    }

    @Test(expected = CityNameAlreadyUsedException.class)
    public void callCreateCityWithAlreadyUsedName() throws CityNameAlreadyUsedException {
        CityDto cityDto = CityDto.builder()
                .name("test")
                .build();

        when(cityService.createCity(cityDto)).thenThrow(new CityNameAlreadyUsedException());
        cityController.createCity(cityDto);
    }

    @Test(expected = CityNotFoundException.class)
    public void callUpdateCityWithInvalidId() throws CityNameAlreadyUsedException {
        Integer id = 5;
        CityDto cityDto = CityDto.builder()
                .name("test")
                .build();

        when(cityService.updateCity(id,cityDto)).thenThrow(new CityNotFoundException());
        cityController.updateCity(id,cityDto);
    }


    @Test(expected = CityNameAlreadyUsedException.class)
    public void callUpdateCityWithAlreadyUsedName() throws CityNameAlreadyUsedException {
        Integer id = 5;
        CityDto cityDto = CityDto.builder()
                .name("test")
                .build();

        when(cityService.updateCity(id,cityDto)).thenThrow(new CityNameAlreadyUsedException());
        cityController.updateCity(id,cityDto);
    }

    @Test
    public void callUpdateCityOk() throws CityNameAlreadyUsedException {
        Integer id = 5;
        CityDto cityDto = CityDto.builder()
                .name("test")
                .build();

        City city = City.builder()
                .id(id)
                .name(cityDto.getName())
                .isActive(Boolean.TRUE)
                .build();

        when(cityService.updateCity(id,cityDto)).thenReturn(city);
        ResponseEntity<CityDto> response = cityController.updateCity(id,cityDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(cityDto.getName(),response.getBody().getName());
        assertEquals(city.getId(),response.getBody().getId());
    }

    @Test
    public void callDeleteCityOk(){
        Integer id = 5;
        City city = City.builder()
                .id(id)
                .name("test")
                .isActive(Boolean.FALSE)
                .build();

        when(cityService.deleteCity(id)).thenReturn(city);
        ResponseEntity<CityDto> response = cityController.deleteCity(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(city.getId(),response.getBody().getId());
    }

    @Test(expected = CityNotFoundException.class)
    public void callDeleteCityWithInvalidId(){
        Integer id = 5;
        when(cityService.deleteCity(id)).thenThrow(new CityNotFoundException());

        cityController.deleteCity(id);
    }


}