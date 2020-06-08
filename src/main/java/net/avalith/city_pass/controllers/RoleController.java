package net.avalith.city_pass.controllers;


import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.dto.RoleDto;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("")
    public ResponseEntity<List<Role>> getAllRoles(){

        return ResponseEntity.ok(roleService.getAllRole());
    }

    @PostMapping("")
    public ResponseEntity<?> createRole(@Valid @RequestBody RoleDto roleDto ){
        URI uri = roleService.createRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(uri);
    }
}
