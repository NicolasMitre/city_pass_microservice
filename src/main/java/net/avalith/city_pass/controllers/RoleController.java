package net.avalith.city_pass.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.RoleDto;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Role" , description = "Role CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a List of All Roles")})
    @GetMapping("")
    public ResponseEntity<List<Role>> getAllRoles(){

        return ResponseEntity.ok(roleService.getAllRole());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a Role By Id")})
    @GetMapping("/{idRole}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable(name = "idRole") Integer idRole){
        return ResponseEntity.ok(roleService.getById(idRole));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success on Create Role"),
            @ApiResponse(code = 201, message = "Create a Rol Successfully")})
    @PostMapping("")
    public ResponseEntity<Role> createRole(@Valid @RequestBody RoleDto roleDto ){
        Role role = roleService.createRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }
}

