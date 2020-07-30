package net.avalith.city_pass.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.ExcursionDto;
import net.avalith.city_pass.dto.response.ListExcursionDto;
import net.avalith.city_pass.exceptions.ExcursionNameAlreadyUsedException;
import net.avalith.city_pass.exceptions.ExcursionNotFoundException;
import net.avalith.city_pass.models.Excursion;
import net.avalith.city_pass.services.ExcursionService;
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
@Api(tags = "Excursion" , description = "Excursion CRUD")
@RequiredArgsConstructor
@RestController
@RequestMapping("/excursion")
public class ExcursionController {
    private final ExcursionService excursionService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a List of All Excursions of a City"),
            @ApiResponse(code = 204, message = "No Excursions Where Found in that City")})
    @GetMapping("")
    public ResponseEntity<ListExcursionDto> getExcursions(@RequestParam(required = false, name = "cityName")String cityName,
                                                          @RequestParam(required = false,name = "name") String excursionName) {
        List<Excursion> list = excursionService.getAllExcursions(cityName,excursionName);
        return (list.size() > 0) ? ResponseEntity.ok(ListExcursionDto.fromExcursionsList(list))
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a Excursion by Id of a City")})
    @GetMapping("/{idExcursion}")
    public ResponseEntity<ExcursionDto> getExcursionById(@PathVariable(name = "idExcursion") Integer idExcursion) {
        Excursion excursion = excursionService.getById(idExcursion);
        return ResponseEntity.ok(ExcursionDto.fromExcursion(excursion));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succes on Create a Excursion"),
            @ApiResponse(code = 201, message = "Create a Excursion Successfully")})
    @PostMapping("")
    public ResponseEntity<ExcursionDto> createExcursion(@Valid @RequestBody ExcursionDto excursionDto) throws
            ExcursionNameAlreadyUsedException {
        Excursion excursion = excursionService.createExcursion(excursionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ExcursionDto.fromExcursion(excursion));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update Excursion Correctly")})
    @PutMapping("/{idExcursion}")
    public ResponseEntity<ExcursionDto> updateExcursion(@PathVariable(name = "idExcursion") Integer idExcursion,
                                                        @Valid @RequestBody ExcursionDto excursionDto) throws
            ExcursionNotFoundException, ExcursionNameAlreadyUsedException {

        return ResponseEntity.ok(ExcursionDto.fromExcursion(excursionService.updateExcursion(idExcursion, excursionDto)));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success on Delete a Excursion")})
    @DeleteMapping("/{idExcursion}")
    public ResponseEntity<ExcursionDto> deleteExcursion(@PathVariable(name = "idExcursion") Integer idExcursion) throws
            ExcursionNotFoundException {
        ExcursionDto excursionDto = ExcursionDto.fromExcursion(excursionService.deleteExcursion(idExcursion));
        return ResponseEntity.ok(excursionDto);
    }

}
