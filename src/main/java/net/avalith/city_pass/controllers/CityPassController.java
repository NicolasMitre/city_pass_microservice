package net.avalith.city_pass.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityPassDto;
import net.avalith.city_pass.dto.response.ListCityPassDto;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.services.CityPassService;
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

@Api(tags = "CityPass" , description = "CityPass CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/city_pass")
public class CityPassController {
    private final CityPassService cityPassService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a List of All CityPasses"),
            @ApiResponse(code = 204, message = "No CityPass Where Found")})
    @GetMapping("")
    public ResponseEntity<ListCityPassDto> getAllCityPasses(){
        List<CityPass> cityPassList = cityPassService.getAllCityPasses();
        return (cityPassList.size() > 0) ? ResponseEntity.ok(ListCityPassDto.fromListDto(cityPassList))
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a CityPass By Its Id")})
    @GetMapping("/{idCityPass}")
    public ResponseEntity<CityPass> getCityPassById(@PathVariable(name = "idCityPass") Integer idCityPass){
        CityPass cityPass = cityPassService.getById(idCityPass);
        return ResponseEntity.ok(cityPass);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succes on Create CityPass"),
            @ApiResponse(code = 201, message = "Create a CityPass Successfully")})
    @PostMapping("")
    public ResponseEntity<CityPass> createCityPass(@Valid @RequestBody CityPassDto cityPassDto){
        CityPass cityPass = cityPassService.createCityPass(cityPassDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cityPass);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update CityPass Correctly")})
    @PutMapping("/{idCityPass}")
    public ResponseEntity<CityPass> modifyCityPassById(@PathVariable(name = "idCityPass") Integer idCityPass,
                                                @Valid @RequestBody CityPassDto cityPassDto) {
        CityPass cityPass = cityPassService.updateCityPass(idCityPass, cityPassDto);
        return ResponseEntity.ok(cityPass);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success on Delete CityPass")})
    @DeleteMapping("/{idCityPass}")
    public ResponseEntity<CityPass> deleteCityPassById(@PathVariable(name = "idCityPass") Integer idCityPass){
        return ResponseEntity.ok(cityPassService.deleteCityPass(idCityPass));
    }
}

