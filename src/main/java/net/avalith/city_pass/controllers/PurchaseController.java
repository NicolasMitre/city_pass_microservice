package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.TicketDto;
import net.avalith.city_pass.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final TicketService ticketService;

    /*@GetMapping("/user/{idUser}")
    public ResponseEntity<?> getAllPurchasesByClient(@PathVariable(name = "idUser")Integer idUser){
        List<>
        return (list.size() >0 )? ResponseEntity.ok(ListExcursionDto.fromExcursionsList(list))
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }*/


    @PostMapping("/ticket")
    public ResponseEntity test(@Valid @RequestBody TicketDto ticketDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.processData(ticketDto));
    }
}
