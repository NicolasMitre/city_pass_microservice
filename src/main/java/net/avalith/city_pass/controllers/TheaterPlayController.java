package net.avalith.city_pass.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.TheaterPlayDto;
import net.avalith.city_pass.dto.response.TheaterPlayListDto;
import net.avalith.city_pass.models.TheaterPlay;
import net.avalith.city_pass.services.TheaterPlayService;
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

@Api(tags = "TheaterPlay" , description = "TheaterPlay CRUD")
@RequiredArgsConstructor
@RestController
@RequestMapping("/theaterplay")
public class TheaterPlayController {
    private final TheaterPlayService theaterPlayService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a List of All TheaterPlays"),
            @ApiResponse(code = 204, message = "No TheaterPlay Where Found")})
    @GetMapping("")
    public ResponseEntity<TheaterPlayListDto> findAllTheaterPlay(){
        List<TheaterPlay> list = this.theaterPlayService.getAll();
        return (list.size() >0 ) ? ResponseEntity.ok(TheaterPlayListDto.fromListTheaterPlay(list)) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a TheaterPlay By Id")})
    @GetMapping("/{idTheaterPlay}")
    public ResponseEntity<TheaterPlayDto> findByTheaterId(@Valid @PathVariable(name = "idTheaterPlay") Integer id){
        TheaterPlay theaterPlay = this.theaterPlayService.getById(id);
        TheaterPlayDto dto = new TheaterPlayDto(theaterPlay);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a TheaterPlay By Name")})
    @GetMapping(value = "",params = "theaterPlayName")
    public ResponseEntity<TheaterPlayDto> findByTheaterPlayName(@Valid @RequestParam(name = "theaterPlayName") String theaterPlayName) {
        TheaterPlay theaterPlay = this.theaterPlayService.getByName(theaterPlayName);
        TheaterPlayDto dto = new TheaterPlayDto(theaterPlay);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a List of TheaterPlay By City name")})
    @GetMapping("/city/{cityName}")
    public ResponseEntity<TheaterPlayListDto> findByCityName(@Valid @PathVariable(value = "cityName") String cityName){
        List<TheaterPlay> theaterListPlay = this.theaterPlayService.getByCityName(cityName);
        return ResponseEntity.status(HttpStatus.OK).body(TheaterPlayListDto.fromListTheaterPlay(theaterListPlay));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success on Create TheaterPlay"),
            @ApiResponse(code = 201, message = "Create a TheaterPlay Successfully")})
    @PostMapping("")
    public ResponseEntity<TheaterPlayDto> createTheaterPlay(@Valid @RequestBody TheaterPlayDto theaterPlayDto){
        TheaterPlay theaterPlay = theaterPlayService.createTheaterPlay(theaterPlayDto);
        TheaterPlayDto dto = new TheaterPlayDto(theaterPlay);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "TheaterPlay Update Correctly")})
    @PutMapping("/{idTheaterPlay}")
    public ResponseEntity<TheaterPlayDto> updateUser( @PathVariable(name = "idTheaterPlay") Integer id, @Valid @RequestBody TheaterPlayDto theaterPlayDto){
        TheaterPlay theaterPlay = theaterPlayService.updateTheaterPlay(id,theaterPlayDto);
        TheaterPlayDto dto = new TheaterPlayDto(theaterPlay);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete a City By id Successfully")})
    @DeleteMapping("/{idTheaterPlay}")
    public ResponseEntity<TheaterPlayDto> deleteTheaterPlay(@Valid @PathVariable(name = "idTheaterPlay") Integer id){
        TheaterPlay theaterPlay = this.theaterPlayService.logicDelete(id);
        TheaterPlayDto dto = new TheaterPlayDto(theaterPlay);
        return ResponseEntity.ok(dto);
    }
}
