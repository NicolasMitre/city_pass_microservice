package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.UserDto;
import net.avalith.city_pass.models.User;
import net.avalith.city_pass.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public List<UserDto> getAllUser(){
        return this.userService.getAll();
    }

    @GetMapping("/{idUser}")
    public UserDto getUserById(@PathVariable(name = "idUser") Integer id){
        return this.userService.getById(id);
    }
    
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto){
        URI uri = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(uri);
    }
}
