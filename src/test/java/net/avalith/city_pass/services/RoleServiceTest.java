package net.avalith.city_pass.services;

import net.avalith.city_pass.dto.RoleDto;
import net.avalith.city_pass.exceptions.RoleNotFoundException;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.repositories.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void getAllRoleSuccessfully() {
        List<Role> list = Arrays.asList(new Role(1, "Administrador"));
        when(roleRepository.findAll()).thenReturn(list);
        List<Role> listRoleTest = roleService.getAllRole();
        assertEquals(1, listRoleTest.size());
    }

    @Test
    public void createRoleSuccessfully() {
        RoleDto roleDto = new RoleDto("Administrator");

        Role savedRole = Role.builder()
                .id(1)
                .name(roleDto.getName())
                .build();

        Role role = Role.builder()
                .name(roleDto.getName())
                .build();

        when(roleRepository.save(role)).thenReturn(savedRole);
        Role roleReturn = roleService.createRole(roleDto);
        assertEquals("Administrator", roleReturn.getName());
        assertEquals(Integer.valueOf(1), roleReturn.getId());
    }

    @Test(expected = RoleNotFoundException.class)
    public void getByIdExpectedAnExpection() {
        when(roleRepository.findById(1)).thenReturn(Optional.empty());
        roleService.getById(1);
    }

    @Test(expected = RoleNotFoundException.class)
    public void getByNameSuccessfully() {
        String dto = "Administrator";

        when(roleRepository.findByName(dto)).thenReturn(Optional.empty());
        roleService.getByName(dto);
    }
}
