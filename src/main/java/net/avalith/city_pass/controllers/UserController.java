package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.UserDto;
import net.avalith.city_pass.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
