package net.avalith.city_pass.controllers;

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

@RequiredArgsConstructor
@RestController
@RequestMapping("/excursion")
public class ExcursionController {
    private final ExcursionService excursionService;

    @GetMapping("/")
    public ResponseEntity<ListExcursionDto> getAllExcursions(@RequestParam(required = false, value = "excursionName") String excursionName) throws ExcursionNotFoundException {
        List<Excursion> list = excursionService.getAllActiveExcursions();
        return (list.size() > 0) ? ResponseEntity.ok(ListExcursionDto.fromExcursionsList(list))
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("")
    public ResponseEntity<ExcursionDto> getExcursionByName(@RequestParam(name = "name") String excursionName) {
        Excursion excursion = excursionService.getByNameActive(excursionName);
        return ResponseEntity.ok(ExcursionDto.fromExcursion(excursion));
    }

    @GetMapping("")
    public ResponseEntity<ListExcursionDto> getAllExcursionsByCity(@RequestParam(name = "cityName") String cityName) {
        List<Excursion> list = excursionService.getAllActiveExcursionsByCity(cityName);
        return (list.size() > 0) ? ResponseEntity.ok(ListExcursionDto.fromExcursionsList(list)) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idExcursion}")
    public ResponseEntity<ExcursionDto> getExcursionById(@PathVariable(name = "idExcursion") Integer idExcursion) throws ExcursionNotFoundException {
        Excursion excursion = excursionService.getById(idExcursion);
        return ResponseEntity.ok(ExcursionDto.fromExcursion(excursion));
    }

    @PostMapping("")
    public ResponseEntity<ExcursionDto> createExcursion(@Valid @RequestBody ExcursionDto ExcursionDto) throws
            ExcursionNameAlreadyUsedException {
        Excursion excursion = excursionService.createExcursion(ExcursionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ExcursionDto.fromExcursion(excursion));
    }

    @PutMapping("/{idExcursion}")
    public ResponseEntity<ExcursionDto> updateExcursion(@PathVariable(name = "idExcursion") Integer idExcursion,
                                                        @Valid @RequestBody ExcursionDto ExcursionDto) throws
            ExcursionNotFoundException, ExcursionNameAlreadyUsedException {

        ExcursionDto excursionDto = ExcursionDto.fromExcursion(excursionService.updateExcursion(idExcursion, ExcursionDto));
        return ResponseEntity.ok(excursionDto);
    }

    @DeleteMapping("/{idExcursion}")
    public ResponseEntity<ExcursionDto> deleteExcursion(@PathVariable(name = "idExcursion") Integer idExcursion) throws
            ExcursionNotFoundException {
        ExcursionDto excursionDto = ExcursionDto.fromExcursion(excursionService.deleteExcursion(idExcursion));
        return ResponseEntity.ok(excursionDto);
    }

}
