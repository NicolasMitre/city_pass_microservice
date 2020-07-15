package net.avalith.city_pass.controller;


import net.avalith.city_pass.controllers.RoleController;
import net.avalith.city_pass.dto.RoleDto;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.services.RoleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RoleControllerTest {
    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void getAllRolesSuccessfully(){
        List<Role> rolesList = new ArrayList<>();

        Role role = Role.builder()
                .idRole(1)
                .name("Jefe")
                .build();

        rolesList.add(role);

        when(this.roleService.getAllRole()).thenReturn(rolesList);

        ResponseEntity<List<Role>> responseEntity = this.roleController.getAllRoles();

        Assert.assertEquals("Jefe", responseEntity.getBody().get(0).getName());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getRoleByTheirIdSuccessfully(){
        RoleDto roleDto = RoleDto.builder()
                .name("admin")
                .build();

        when(this.roleService.getById(1)).thenReturn(roleDto);

        ResponseEntity<RoleDto> responseEntity = this.roleController.getRoleById(1);

        Assert.assertEquals("admin", responseEntity.getBody().getName());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void addNewRoleSuccessfully(){
        RoleDto roleDto = RoleDto.builder()
                .name("admin")
                .build();

        Role role = Role.builder()
                .idRole(1)
                .name("user")
                .build();

        when(this.roleService.createRole(roleDto)).thenReturn(role);

        ResponseEntity<Role> responseEntity = this.roleController.createRole(roleDto);

        Assert.assertEquals("user", responseEntity.getBody().getName());
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}

