package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.TheaterPlayDto;
import net.avalith.city_pass.models.TheaterPlay;
import net.avalith.city_pass.services.TheaterPlayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/theatherplay")
public class TheaterPlayController {
    private final TheaterPlayService theaterPlayService;

    @GetMapping("")
    public ResponseEntity<List<TheaterPlayDto>> findAllTheaterPlay(){
        List<TheaterPlayDto> listDto = this.theaterPlayService.getAll();
        return (listDto.size() >0 ) ? ResponseEntity.ok(listDto) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("")
    public ResponseEntity<TheaterPlayDto> createTheaterPlay(@Valid @RequestBody TheaterPlayDto theaterPlayDto){
        TheaterPlay
    }
}
