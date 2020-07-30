package net.avalith.city_pass.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(tags = "User" , description = "User CRUD")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Found Successfully"),
            @ApiResponse(code = 204, message = "User Not Found")})
    @GetMapping("")
    public ResponseEntity<UserListDto> getAllUser() {
        List<User> list = this.userService.getAll();
        return (list.size() > 0) ? ResponseEntity.ok(UserListDto.fromListDto(list)) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a User By Id")})
    @GetMapping("/{idUser}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "idUser") Integer id) {
        User user = this.userService.getById(id);
        UserDto dto = new UserDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success on Create a User"),
            @ApiResponse(code = 201, message = "Create a User Successfully")})
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        UserDto dto = new UserDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success on Update User")})
    @PutMapping("/{idUser}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "idUser") Integer id, @Valid @RequestBody UserDto userDto) {
        User user = userService.updateUser(id, userDto);
        UserDto dto = new UserDto(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success on Delete a User")})
    @DeleteMapping("/{idUser}")
    public ResponseEntity<UserDto> deleteUser(@Valid @PathVariable(name = "idUser") Integer id) {
        User user = this.userService.logicDelete(id);
        UserDto dto = new UserDto(user);
        return ResponseEntity.ok(dto);
    }


}
