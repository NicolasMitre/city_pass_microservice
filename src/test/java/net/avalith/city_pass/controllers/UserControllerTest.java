package net.avalith.city_pass.controllers;

import net.avalith.city_pass.dto.UserListDto;
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

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserControllerTest {
    @Mock
    private UserService userServiceMockito;
    @InjectMocks
    private UserController userController;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void getAllUserVoidTest(){

        when(this.userServiceMockito.getAll()).thenReturn(Collections.emptyList());
        ResponseEntity<UserListDto> response = userController.getAllUser();
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    private User createUser(){
        Role rol = Role.builder().id(1).name("admin").build();
        Set<Role> setRole = new HashSet<>();
        setRole.add(rol);
        User user = User.builder().name("Mauro").roles(setRole).username("cucamonga").isActive(Boolean.TRUE).build();
        return  user;
    }

    @Test
    public void getAllUserTest(){
        List<User> userList = new ArrayList<>();
        userList.add(createUser());
        when(this.userServiceMockito.getAll()).thenReturn(userList);
        ResponseEntity<UserListDto> response = userController.getAllUser();
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void
}

public class CallControllerTest {
    CallController callController;

    @Mock
    CallService callService;

    @Before
    public void setUp(){
        initMocks(this);
        callController = new CallController(callService);
    }

    @Test(expected = ParseException.class)
    public void testGetDurationInMonthWrongFormat() throws ParseException {
        String yearMonth = "01-5855";
        when(callService.getDurationInMonth(yearMonth)).thenThrow(new ParseException("Formato erroneo",1));
        callController.getPersonDurationInMonth(yearMonth);
    }

    @Test
    public void testGetDurationInMonthNoContent() throws ParseException {
        String yearMonth = "2020-05";
        when(callService.getDurationInMonth(yearMonth)).thenReturn(Collections.emptyList());
        ResponseEntity<List<PersonDuration>> response = callController.getPersonDurationInMonth(yearMonth);

        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }
}
