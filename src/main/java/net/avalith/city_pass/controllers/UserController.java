package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.UserDto;
import net.avalith.city_pass.dto.response.UserListDto;
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
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<UserListDto> getAllUser(){
        List<User> list = this.userService.getAll();
        return (list.size() > 0) ? ResponseEntity.ok(UserListDto.fromListDto(list)) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "idUser") Integer id){
        User user = this.userService.getById(id);
        UserDto dto = new UserDto(user);
        return ResponseEntity.status(HttpStatus.FOUND).body(dto);
    }
    
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        User user = userService.createUser(userDto);
        UserDto dto = new UserDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<UserDto> updateUser( @PathVariable(name = "idUser") Integer id, @Valid @RequestBody UserDto userDto){
        User user= userService.updateUser(id,userDto);
        UserDto dto = new UserDto(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<UserDto> deleteUser(@Valid @PathVariable(name = "idUser") Integer id)
    {
        User user = this.userService.logicDelete(id);
        UserDto dto = new UserDto(user);
        return ResponseEntity.ok(dto);
    }



}
