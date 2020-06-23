package net.avalith.city_pass.services;

import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.exceptions.CityNameAlreadyUsedException;
import net.avalith.city_pass.exceptions.CityNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.repositories.CityRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CityServiceTest {
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Before
    public void startUp() {
        initMocks(this);
    }

    @Test(expected = CityNotFoundException.class)
    public void callGetByNameWithAnInvalidName() {
        String name = "test";
        when(cityRepository.findByNameAndIsActive(name, Boolean.TRUE)).thenReturn(Optional.ofNullable(null));
        cityService.getByNameAndIsActive(name);
    }

    @Test
    public void callGetByNameOk() {
        String name = "test";
        City city = City.builder()
                .name(name)
                .id(1)
                .isActive(Boolean.TRUE)
                .build();

        when(cityRepository.findByNameAndIsActive(name, Boolean.TRUE)).thenReturn(Optional.ofNullable(city));
        City returnedCity = cityService.getByNameAndIsActive(name);

        assertEquals(name, returnedCity.getName());
        assertEquals(Boolean.TRUE, returnedCity.getIsActive());
    }

    @Test(expected = CityNotFoundException.class)
    public void callGetByIdWithAnInvalidId() {
        Integer id = 465;
        when(cityRepository.findByIdAndIsActive(id, Boolean.TRUE)).thenReturn(Optional.ofNullable(null));
        cityService.getById(id);
    }

    @Test
    public void callGetByIdOk() {
        Integer id = 3;
        City city = City.builder()
                .name("test")
                .id(id)
                .isActive(Boolean.TRUE)
                .build();

        when(cityRepository.findByIdAndIsActive(id, Boolean.TRUE)).thenReturn(Optional.ofNullable(city));
        City returnedCity = cityService.getById(id);

        assertEquals(id, returnedCity.getId());
        assertEquals(Boolean.TRUE, returnedCity.getIsActive());
    }

    @Test
    public void callGetAllCitiesReturnWithContent() {
        City city1 = City.builder()
                .name("test")
                .id(1)
                .isActive(Boolean.TRUE)
                .build();
        City city2 = City.builder()
                .name("test")
                .id(1)
                .isActive(Boolean.TRUE)
                .build();
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        when(cityRepository.findAllByIsActive(Boolean.TRUE)).thenReturn(cities);
        List<City> returnedCities = cityService.getAllCities();

        assertEquals(cities.size(), returnedCities.size());
    }

    @Test
    public void callGetAllCitiesReturnEmpty() {
        List<City> cities = Collections.emptyList();
        when(cityRepository.findAllByIsActive(Boolean.TRUE)).thenReturn(cities);
        List<City> returnedCities = cityService.getAllCities();

        assertEquals(0, returnedCities.size());
    }

    @Test(expected = CityNotFoundException.class)
    public void callDeleteCityWithInvalidId(){
        Integer id = 465;
        when(cityRepository.findByIdAndIsActive(id, Boolean.TRUE)).thenReturn(Optional.ofNullable(null));
        cityService.deleteCity(id);
    }

    @Test
    public void callDeleteCityWithValidId(){
        Integer id = 465;
        City city = City.builder()
                .name("test")
                .id(id)
                .isActive(Boolean.TRUE)
                .build();
        City deletedCity = City.builder()
                .name("test")
                .id(id)
                .isActive(Boolean.FALSE)
                .build();

        when(cityRepository.findByIdAndIsActive(id, Boolean.TRUE)).thenReturn(Optional.ofNullable(city));
        when(cityRepository.save(city)).thenReturn(deletedCity);

        City testCity = cityService.deleteCity(id);

        assertEquals(id, deletedCity.getId());
        assertEquals(deletedCity.getIsActive(), testCity.getIsActive());
    }

    @Test(expected = CityNotFoundException.class)
    public void callUpdateCityWithInvalidId() throws CityNameAlreadyUsedException {
        Integer id = 465;
        City city = City.builder()
                .name("test")
                .id(id)
                .isActive(Boolean.TRUE)
                .build();

        when(cityRepository.findByIdAndIsActive(id, Boolean.TRUE)).thenReturn(Optional.ofNullable(null));

        cityService.updateCity(id,new CityDto());
    }

    @Test
    public void callUpdateCityOk() throws CityNameAlreadyUsedException {
        Integer id = 465;
        City searchedcity = City.builder()
                .name("test")
                .id(id)
                .isActive(Boolean.TRUE)
                .build();

        CityDto cityDto = CityDto.builder()
                .name("cambiado")
                .build();

        City updatedCity = City.builder()
                .id(searchedcity.getId())
                .isActive(searchedcity.getIsActive())
                .name(cityDto.getName())
                .build();

        when(cityRepository.findByIdAndIsActive(id, Boolean.TRUE)).thenReturn(Optional.ofNullable(searchedcity));
        when(cityRepository.save(updatedCity)).thenReturn(updatedCity);

        City testCity = cityService.updateCity(id,cityDto);

        assertEquals(updatedCity.getName(),testCity.getName());
        assertEquals(updatedCity.getId(), testCity.getId());
    }

    @Test(expected = CityNameAlreadyUsedException.class)
    public void callUpdateCityWithAlreadyUsedName() throws CityNameAlreadyUsedException {
        Integer id = 465;
        City searchedcity = City.builder()
                .name("test")
                .id(id)
                .isActive(Boolean.TRUE)
                .build();

        CityDto cityDto = CityDto.builder()
                .name("cambiado")
                .build();

        City updatedCity = City.builder()
                .id(searchedcity.getId())
                .isActive(searchedcity.getIsActive())
                .name(cityDto.getName())
                .build();

        when(cityRepository.findByIdAndIsActive(id, Boolean.TRUE)).thenReturn(Optional.ofNullable(searchedcity));
        when(cityRepository.save(updatedCity)).thenThrow(new DataIntegrityViolationException("string"));

        cityService.updateCity(id,cityDto);
    }



    @Test
    public void callCreateCityOk() throws CityNameAlreadyUsedException {
        CityDto cityDto = CityDto.builder()
                .name("ciudad")
                .build();

        City createdCity = City.builder()
                .name(cityDto.getName())
                .isActive(Boolean.TRUE)
                .build();

        City savedCity = City.builder()
                .id(3)
                .name(cityDto.getName())
                .isActive(Boolean.TRUE)
                .build();

        when(cityRepository.findByName(cityDto.getName())).thenReturn(null);
        when(cityRepository.save(createdCity)).thenReturn(savedCity);


        City cityTest = cityService.createCity(cityDto);
        assertEquals(Integer.valueOf(3),cityTest.getId());
        assertEquals(cityDto.getName(),cityTest.getName());
    }

    @Test(expected = CityNameAlreadyUsedException.class)
    public void callCreateCityWithAlreadyUsedName() throws CityNameAlreadyUsedException {
        CityDto cityDto = CityDto.builder()
                .name("ciudad")
                .build();

        City createdCity = City.builder()
                .name(cityDto.getName())
                .isActive(Boolean.TRUE)
                .build();

        when(cityRepository.save(createdCity)).thenThrow(new DataIntegrityViolationException("string"));

        City cityTest = cityService.createCity(cityDto);
    }
}
