package net.avalith.city_pass.services;

import net.avalith.city_pass.dto.CityPassDto;
import net.avalith.city_pass.exceptions.CityPassNotFoundException;
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

    @InjectMocks
    private CityPassService cityPassService;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void getAllCityPasses() {
        CityPass cityPass = new CityPass(1,
                "Mar del chetaje",
                "Chetoslovakia",
                10d,
                10, Boolean.TRUE);
        List<CityPass> list = new ArrayList<>();
        list.add(cityPass);
        when(cityPassRepository.findAllByIsActive(true)).thenReturn(list);
        List<CityPass> listCityPassTest = cityPassService.getAllCityPasses();
        assertEquals(1, listCityPassTest.size());
    }

    @Test
    public void createCityPass() {
        CityPassDto cityPassDto = CityPassDto.builder()
                .name("Pack de compras 1")
                .description("Andate de compras")
                .days(1)
                .price(10d)
                .build();

        CityPass cityPassSaved = CityPass.builder()
                .id(1)
                .name("Pack de compras 1")
                .description("Andate de compras")
                .days(1)
                .price(10d)
                .build();

        CityPass cityPass = CityPass.builder()
                .name("Pack de compras 1")
                .description("Andate de compras")
                .days(1)
                .price(10d)
                .build();



        when(cityPassRepository.save(cityPass)).thenReturn(cityPassSaved);
        CityPass cityPassReturn = cityPassService.createCityPass(cityPassDto);
        assertEquals(Integer.valueOf(1), cityPassReturn.getId());
        assertEquals("Pack de compras 1", cityPassReturn.getName());
        assertEquals("Andate de compras", cityPassReturn.getDescription());
        assertEquals(Integer.valueOf(1), cityPassReturn.getDays());
        assertEquals(Double.valueOf(10), cityPassReturn.getPrice());
    }

    @Test(expected = CityPassNotFoundException.class)
    public void getById() {
        when(cityPassRepository.findByIdAndIsActive(1, true))
                .thenReturn(Optional.ofNullable(null));
        cityPassService.getById(1);
    }

    @Test(expected = CityPassNotFoundException.class)
    public void updateCityPassWithAnIdInvalid() {
        CityPassDto cityPassDto = CityPassDto.builder()
                .name("ajsd")
                .description("123897")
                .days(1)
                .price(10d)
                .build();

        when(cityPassRepository.findByIdAndIsActive(1, true))
                .thenReturn(Optional.ofNullable(null));
        cityPassService.updateCityPass(2, cityPassDto);
    }

    @Test(expected = CityPassNotFoundException.class)
    public void deleteCityPass() {
        when(cityPassRepository.findByIdAndIsActive(1, true))
                .thenReturn(Optional.ofNullable(null));
        cityPassService.deleteCityPass(2);
    }
}


