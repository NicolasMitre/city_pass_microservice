package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.RoleDto;
import net.avalith.city_pass.exceptions.RoleNotFoundException;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public URI createRole(RoleDto roleDto) {
        Role role = Role.builder().name(roleDto.getName()).build();
        role = roleRepository.save(role);
        return getLocation(role);
    }

    private URI getLocation(Role role) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(role.getId())
                .toUri();
    }

    public RoleDto getById(Integer idRole) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(RoleNotFoundException::new);

        RoleDto roleDto = new RoleDto();
        roleDto.fromRole(role);
        return roleDto;
    }

    public Role getByName(String dto) {
        return roleRepository.findByName(dto)
                .orElseThrow(RoleNotFoundException::new);
    }
}
