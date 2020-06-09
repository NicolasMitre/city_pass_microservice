package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.RoleDto;
import net.avalith.city_pass.dto.UserDto;
import net.avalith.city_pass.exceptions.RoleNotFoundException;
import net.avalith.city_pass.exceptions.UserNotFoundException;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.models.User;
import net.avalith.city_pass.repositories.RoleRepository;
import net.avalith.city_pass.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<UserDto> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toList());
    }

    public UserDto getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        System.out.println(user);
        UserDto userDto = new UserDto(user);
        return userDto;
    }

    public URI createUser(UserDto userDto) {

        User user = User.builder().name(userDto.getName())
                                  .username(userDto.getUsername())
                                  .roles(findRoles(userDto.getRoles()))
                        .build();
        user = userRepository.save(user);
        return getLocation(user);
    }

    private Set<Role> findRoles(List<String> roles){
        Set<Role> roleSet = new HashSet<>();
        for(String rolename : roles){
            roleSet.add(this.roleRepository.findByName(rolename).orElseThrow(RoleNotFoundException::new));
        }
        return roleSet;
    }


    private URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
    }


}
