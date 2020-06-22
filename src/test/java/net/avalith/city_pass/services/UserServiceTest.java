package net.avalith.city_pass.services;

import net.avalith.city_pass.dto.UserDto;
import net.avalith.city_pass.exceptions.UserNotFoundException;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.models.User;
import net.avalith.city_pass.repositories.RoleRepository;
import net.avalith.city_pass.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @Mock
    UserRepository userRepositoryMockito;

    @Mock
    RoleRepository roleRepositoryMockito;

    @InjectMocks
    UserService userService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    private Set<Role> setRole() {
        Role rol = Role.builder().id(1).name("admin").build();
        Set<Role> setRole = new HashSet<>();
        setRole.add(rol);
        return setRole;
    }

    private Role createRole() {
        return Role.builder()
                .id(1)
                .name("admin")
                .build();
    }

    private User createUser(Integer id, String name, String userName, Boolean status) {
        return User.builder()
                .id(id)
                .name(name)
                .roles(setRole())
                .username(userName)
                .isActive(status)
                .build();

    }

    @Test
    public void getAllTest() {
        User user = createUser(1, "Mauro", "Cucamonga", Boolean.TRUE);
        User user2 = createUser(2, "Pepe", "Argento", Boolean.TRUE);
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        when(this.userRepositoryMockito.findAllByIsActive(Boolean.TRUE)).thenReturn(list);
        Assert.assertEquals(list.size(), this.userService.getAll().size());
    }

    @Test
    public void getAllWithNoUserTest() {
        List<User> list = new ArrayList<>();
        when(this.userRepositoryMockito.findAllByIsActive(Boolean.TRUE)).thenReturn(list);
        Assert.assertEquals(list.size(), this.userService.getAll().size());
    }


    @Test
    public void getByIdTest() {
        Integer id = 1;
        User user = createUser(id, "Mauro", "Cucamonga", Boolean.TRUE);
        when(this.userRepositoryMockito.findByIdAndIsActive(id, Boolean.TRUE)).thenReturn(Optional.of(user));
        User userComp = this.userService.getById(id);
        Assert.assertEquals(user, userComp);
    }

    @Test(expected = UserNotFoundException.class)
    public void getByIdNotContentTest() {
        Integer id = -1;
        when(this.userRepositoryMockito.findByIdAndIsActive(id, Boolean.TRUE)).thenThrow(new UserNotFoundException());
        User userComp = this.userService.getById(id);
    }

    @Test
    public void createUser() {

        Role role = createRole();
        User user = createUser(1, "Mauro", "Cucamonga", Boolean.TRUE);
        user.setId(null);
        when(this.userRepositoryMockito.save(user)).thenReturn(user);
        when(this.roleRepositoryMockito.findByName(role.getName())).thenReturn(Optional.of(role));
        UserDto dto = new UserDto(user);
        User userres = this.userService.createUser(dto);
        System.out.println(userres);
        Assert.assertEquals(user, userres);
    }


}
