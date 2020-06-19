package net.avalith.city_pass.controllers;

import net.avalith.city_pass.dto.UserDto;
import net.avalith.city_pass.dto.UserListDto;
import net.avalith.city_pass.exceptions.UserNotFoundException;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.models.User;
import net.avalith.city_pass.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserControllerTest {
    @Mock
    private UserService userServiceMockito;
    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void getAllUserVoidTest() {
        when(this.userServiceMockito.getAll()).thenReturn(Collections.emptyList());
        ResponseEntity<UserListDto> response = userController.getAllUser();
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private Set<Role> setRole(){
        Role rol = Role.builder().id(1).name("admin").build();
        Set<Role> setRole = new HashSet<>();
        setRole.add(rol);
        return setRole;
    }

    private User createUser(Integer id , String name, String userName, Boolean status) {
        return User.builder()
                .id(id)
                .name(name)
                .roles(setRole())
                .username(userName)
                .isActive(status)
                .build();

    }

    @Test
    public void getAllUserTest() {
        List<User> userList = new ArrayList<>();
        Integer id = 1;
        userList.add(createUser(id,"Mauro","Cucamonga",Boolean.TRUE));
        when(this.userServiceMockito.getAll()).thenReturn(userList);
        ResponseEntity<UserListDto> response = userController.getAllUser();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserByIdUnknownTest() {
        Integer id = -1;
        when(this.userServiceMockito.getById(id)).thenThrow(new UserNotFoundException());
        userController.getUserById(id);
    }

    @Test
    public void getUserByIdTest() {
        Integer id = 1;
        User user = createUser(id,"Mauro","Cucamonga",Boolean.TRUE);
        when(this.userServiceMockito.getById(id)).thenReturn(user);
        ResponseEntity<UserDto> responseEntity = userController.getUserById(id);
        Assert.assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void createUserOkTest() {
        Integer id = 1;
        User user = createUser(id,"Mauro","Cucamonga",Boolean.TRUE);
        List<String> roleList = user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
        UserDto toBeSaved = UserDto.builder()
                .name(user.getName())
                .username(user.getUsername())
                .roles(roleList)
                .build();
        when(this.userServiceMockito.createUser(toBeSaved)).thenReturn(user);
        ResponseEntity<UserDto> responseEntity = userController.createUser(toBeSaved);
        UserDto dtoSaved = responseEntity.getBody();
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assert.assertEquals(id, dtoSaved.getId());
        Assert.assertEquals(user.getName(), dtoSaved.getName());
        Assert.assertEquals(user.getUsername(), dtoSaved.getUsername());

    }

    //Todo preguntar por unique en username y las excepciones

    /* public ResponseEntity<UserDto> updateUser( @PathVariable(name = "idUser") Integer id, @Valid @RequestBody UserDto userDto){
        User user= userService.updateUser(id,userDto);
        UserDto dto = new UserDto(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    } */


    @Test
    public void updateUserVoid() {
        Integer id = 1;
        List<String> roleList = setRole().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
        UserDto dto = UserDto.builder()
                .name("Pepe")
                .username("Biondi")
                .roles(roleList)
                .build();
        User user = User.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .roles(setRole())
                .build();
        when(this.userServiceMockito.updateUser(id,dto)).thenReturn(user);
        ResponseEntity<UserDto> responseEntity = this.userController.updateUser(id,dto);
        Assert.assertEquals(HttpStatus.ACCEPTED,responseEntity.getStatusCode());
    }
}

