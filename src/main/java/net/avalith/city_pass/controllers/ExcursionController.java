package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.ExcursionDto;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/excursion")
public class ExcursionController {
    private final ExcursionService excursionService;

    @GetMapping("")
    public ResponseEntity<List<ExcursionDto>> getAllExcursions(){
        List<ExcursionDto> list = excursionService.getAllActiveExcursions();
        return (list.size() >0 )? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/city/{idCity}")
    public ResponseEntity<List<ExcursionDto>> getAllExcursionsByCity(@PathVariable(name = "idCity") Integer idCity){
        List<ExcursionDto> list = excursionService.getAllActiveExcursionsByCity(idCity);
        return (list.size() >0 )? ResponseEntity.ok(list) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idExcursion}")
    public ResponseEntity<ExcursionDto> getExcursionById(@PathVariable(name = "idExcursion") Integer idExcursion){
        return ResponseEntity.ok(excursionService.getById(idExcursion));
    }

    @PostMapping("")
    public ResponseEntity<?> createExcursion(@Valid @RequestBody ExcursionDto ExcursionDto ){
        URI uri = excursionService.createExcursion(ExcursionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(uri);
    }

    @PutMapping("/{idExcursion}")
    public ResponseEntity<?> updateExcursion(@PathVariable(name = "idExcursion")Integer idExcursion, @Valid @RequestBody ExcursionDto ExcursionDto){
        return ResponseEntity.ok(excursionService.updateExcursion(idExcursion,ExcursionDto));
    }

    @DeleteMapping("/{idExcursion}")
    public ResponseEntity<?> deleteExcursion(@PathVariable(name = "idExcursion")Integer idExcursion){
        excursionService.deleteExcursion(idExcursion);
        return ResponseEntity.ok().build();
    }

}
