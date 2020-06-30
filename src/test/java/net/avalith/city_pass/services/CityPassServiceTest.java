package net.avalith.city_pass.services;

import net.avalith.city_pass.dto.CityPassDto;
import net.avalith.city_pass.exceptions.CityPassNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.repositories.CityPassRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CityPassServiceTest {

    @Mock
    private CityPassRepository cityPassRepository;

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityPassService cityPassService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    public void getOneCityPassUsingGetAll() {
        City city = City.builder()
                .id(1)
                .name("Mar del plata")
                .isActive(Boolean.TRUE)
                .build();

        CityPass cityPass = new CityPass(1,
                "Mar del chetaje",
                "Chetoslovakia",
                10d,
                10,
                city,
                Boolean.TRUE);

        List<CityPass> list = new ArrayList<>();
        list.add(cityPass);
        when(cityPassRepository.findAllByIsActive(true)).thenReturn(list);

        List<CityPass> listCityPassTest = cityPassService.getAllCityPasses();
        assertEquals(1, listCityPassTest.size());
    }

    @Test
    public void createNewCityPassSuccessfully() {
        City city = City.builder()
                .id(1)
                .name("Mar del plata")
                .isActive(Boolean.TRUE)
                .build();

        CityPassDto cityPassDto = CityPassDto.builder()
                .name("Pack de compras 1")
                .description("Andate de compras")
                .days(1)
                .price(10d)
                .cityName("Mar del plata")
                .build();

        CityPass cityPassSaved = CityPass.builder()
                .id(1)
                .name("Pack de compras 1")
                .description("Andate de compras")
                .days(1)
                .price(10d)
                .city(city)
                .build();

        CityPass cityPass = CityPass.builder()
                .name("Pack de compras 1")
                .description("Andate de compras")
                .days(1)
                .price(10d)
                .city(city)
                .build();

        when(cityService.getByName(cityPassDto.getCityName())).thenReturn(city);
        when(cityPassRepository.save(cityPass)).thenReturn(cityPassSaved);
        CityPass cityPassReturn = cityPassService.createCityPass(cityPassDto);
        assertEquals(cityPassSaved, cityPassReturn);
    }

    @Test(expected = CityPassNotFoundException.class)
    public void getACityPassAndReturnAndException() {
        when(cityPassRepository.findByIdAndIsActive(1, true))
                .thenReturn(Optional.empty());
        cityPassService.getById(1);
    }

    @Test
    public void getByIdSuccessfully() {
        City city = City.builder()
                .id(1)
                .name("Mar del plata")
                .isActive(Boolean.TRUE)
                .build();

        CityPass cityPassSaved = CityPass.builder()
                .id(1)
                .name("Proband0")
                .description("Alveolos")
                .days(1)
                .price(10d)
                .city(city)
                .build();

        when(cityPassRepository.findByIdAndIsActive(1, true))
                .thenReturn(Optional.ofNullable(cityPassSaved));

        CityPass cityReturn = cityPassService.getById(1);
        assertEquals(Integer.valueOf(1), cityReturn.getId());
        assertEquals("Proband0", cityReturn.getName());
        assertEquals("Alveolos", cityReturn.getDescription());
        assertEquals(Integer.valueOf(1), cityReturn.getDays());
        assertEquals(Double.valueOf(10), cityReturn.getPrice());
    }

    @Test(expected = CityPassNotFoundException.class)
    public void updateCityPassWithAnIdInvalidAndThrowException() {
        CityPassDto cityPassDto = CityPassDto.builder()
                .name("ajsd")
                .description("123897")
                .days(1)
                .price(10d)
                .cityName("Mar del plata")
                .build();

        when(cityPassRepository.findByIdAndIsActive(1, true))
                .thenReturn(Optional.empty());
        cityPassService.updateCityPass(2, cityPassDto);
    }

    @Test(expected = CityPassNotFoundException.class)
    public void deleteCityPassWithInvalidIdAndThrowTheirException() {
        when(cityPassRepository.findByIdAndIsActive(1, true))
                .thenReturn(Optional.empty());
        cityPassService.deleteCityPass(2);
    }
}


