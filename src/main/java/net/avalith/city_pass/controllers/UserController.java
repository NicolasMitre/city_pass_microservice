package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.UserDto;
import net.avalith.city_pass.models.User;
import net.avalith.city_pass.services.UserService;
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
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUser(){
        List<UserDto> listDto = this.userService.getAll();
        return (listDto.size() > 0) ? ResponseEntity.ok(listDto) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "idUser") Integer id){
        UserDto userDto = this.userService.getById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDto);
    }
    
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto){
        URI uri = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(uri);
    }
    @PutMapping("/{idUser}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable(name = "idUser") Integer id,  @RequestBody UserDto userDto){
        User user= userService.updateUser(id,userDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUser(@Valid @PathVariable(name = "idUser") Integer id)
    {
        this.userService.logicDelete(id);
        return ResponseEntity.ok().build();
    }



}
