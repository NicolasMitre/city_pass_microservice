package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityPassDto;
import net.avalith.city_pass.dto.RoleDto;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.services.CityPassService;
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
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city_pass")
public class CityPassController {
    private final CityPassService cityPassService;

    @GetMapping("")
    public ResponseEntity<List<CityPass>> getAllCityPasses(){
        return ResponseEntity.ok(cityPassService.getAllCityPasses());
    }
}

