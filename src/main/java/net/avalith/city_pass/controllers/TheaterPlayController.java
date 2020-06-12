package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.TheaterPlayDto;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/theaterplay")
public class TheaterPlayController {
    private final TheaterPlayService theaterPlayService;

    @GetMapping("")
    public ResponseEntity<List<TheaterPlayDto>> findAllTheaterPlay(){
        List<TheaterPlayDto> listDto = this.theaterPlayService.getAll();
        return (listDto.size() >0 ) ? ResponseEntity.ok(listDto) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idTheaterPlay}")
    public ResponseEntity<TheaterPlayDto> findByTheaterId(@Valid @PathVariable(name = "idTheaterPlay") Integer id){
        TheaterPlayDto dto = this.theaterPlayService.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(dto);
    }

    @PostMapping("")
    public ResponseEntity<TheaterPlayDto> createTheaterPlay(@Valid @RequestBody TheaterPlayDto theaterPlayDto){
        TheaterPlayDto dto = theaterPlayService.createTheaterPlay(theaterPlayDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{idTheaterPlay}")
    public ResponseEntity<TheaterPlayDto> updateUser( @PathVariable(name = "idTheaterPlay") Integer id, @Valid @RequestBody TheaterPlayDto theaterPlayDto){
        TheaterPlayDto dto = theaterPlayService.updateTheaterPlay(id,theaterPlayDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @DeleteMapping("/{idTheaterPlay}")
    public ResponseEntity<TheaterPlayDto> deleteTheaterPlay(@Valid @PathVariable(name = "idTheaterPlay") Integer id){
        TheaterPlayDto dto = this.theaterPlayService.logicDelete(id);
        return ResponseEntity.ok(dto);
    }
}
