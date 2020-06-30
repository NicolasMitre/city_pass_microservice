package net.avalith.city_pass.controller;

import net.avalith.city_pass.controllers.CityPassController;
import net.avalith.city_pass.dto.CityPassDto;
import net.avalith.city_pass.dto.ListCityPassDto;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.services.CityPassService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CityPassControllerTest {
    @Mock
    private CityPassService cityPassService;

    @InjectMocks
    private CityPassController cityPassController;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void getAllCityPassesSuccessfully() {

        List<CityPass> cityPassList = new ArrayList<>();

        CityPass cityPass = CityPass.builder()
                .id(1)
                .name("Mar del plata + 60 excursiones")
                .description("Mas de 280913213 excursiones disponibles")
                .price(100d)
                .days(12)
                .isActive(Boolean.TRUE)
                .build();

        cityPassList.add(cityPass);

        when(this.cityPassService.getAllCityPasses()).thenReturn(cityPassList);

        ResponseEntity<ListCityPassDto> responseEntity = this.cityPassController.getAllCityPasses();

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void calledGetAllCityPassWithoutContentAlready() {

        List<CityPass> cityPassList = new ArrayList<>();

        when(this.cityPassService.getAllCityPasses()).thenReturn(cityPassList);

        ResponseEntity<ListCityPassDto> responseEntity = this.cityPassController.getAllCityPasses();

        Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void addNewCityPassSuccessfully() {

        CityPassDto cityPassDto = CityPassDto.builder()
                .name("Mar del plata + 60 excursiones")
                .description("Mas de 280913213 excursiones disponibles")
                .price(100d)
                .days(12)
                .build();

        CityPass cityPass = CityPass.builder()
                .id(1)
                .name("Mar del plata + 60 excursiones")
                .description("Mas de 280913213 excursiones disponibles")
                .price(100d)
                .days(12)
                .isActive(Boolean.TRUE)
                .build();

        when(this.cityPassService.createCityPass(cityPassDto)).thenReturn(cityPass);

        ResponseEntity<CityPass> responseEntity = this.cityPassController.createCityPass(cityPassDto);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void editAnCityPassSuccessfully() {

        CityPassDto cityPassDto = CityPassDto.builder()
                .name("Mar del plata + 80 excursiones")
                .description("Mas de 280913213 excursiones disponibles")
                .price(100d)
                .days(12)
                .build();

        CityPass cityPass = CityPass.builder()
                .id(1)
                .name("Mar del plata + 80 excursiones")
                .description("Mas de 280913213 excursiones disponibles")
                .price(100d)
                .days(12)
                .isActive(Boolean.TRUE)
                .build();

        when(this.cityPassService.updateCityPass(1, cityPassDto)).thenReturn(cityPass);

        ResponseEntity<CityPass> responseEntity = this.cityPassController.modifyCityPassById(1, cityPassDto);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void deleteAnCityPassSuccessfully() {

        CityPass cityPass = CityPass.builder()
                .id(1)
                .name("Mar del plata + 80 excursiones")
                .description("Mas de 280913213 excursiones disponibles")
                .price(100d)
                .days(12)
                .isActive(Boolean.FALSE)
                .build();

        when(this.cityPassService.deleteCityPass(1)).thenReturn(cityPass);

        ResponseEntity<CityPass> responseEntity = this.cityPassController.deleteCityPassById(1);

        Assert.assertEquals(Integer.valueOf(1), responseEntity.getBody().getId());
        Assert.assertEquals(Boolean.FALSE, responseEntity.getBody().getIsActive());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}

