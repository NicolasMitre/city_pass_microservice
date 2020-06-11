package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.UserDto;
import net.avalith.city_pass.exceptions.RoleNotFoundException;
import net.avalith.city_pass.exceptions.UserNotFoundException;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.models.User;
import net.avalith.city_pass.repositories.RoleRepository;
import net.avalith.city_pass.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<UserDto> getAll() {
        return this.userRepository.findAllByisActive(true)
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public UserDto getById(Integer id) {
        return userRepository.findByIdAndIsActive(id,true)
                .map(UserDto::new)
                .orElseThrow(UserNotFoundException::new);
    }

    public User createUser(UserDto userDto) {

        User user = User.builder().name(userDto.getName())
                .username(userDto.getUsername())
                .roles(findRoles(userDto.getRoles()))
                .isActive(true)
                .build();
        user = userRepository.save(user);
        return user;
    }

    private Set<Role> findRoles(List<String> roleNames){
        return roleNames.stream()
                    .map(roleName -> findRoleByName(roleName))
                    .collect(Collectors.toSet());
        }

    private Role findRoleByName(String roleName) {
        return this.roleRepository.findByName(roleName)
                .orElseThrow(RoleNotFoundException::new);
}

    private URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
    }

    public UserDto updateUser(Integer idUser, UserDto userDto) {
        return this.userRepository.findByIdAndIsActive(idUser, true)
                .map(user -> update(user,userDto))
                        .map(user -> this.userRepository.save(user))
                        .map(UserDto::new)
                        .orElseThrow(UserNotFoundException::new);
    }

    public UserDto logicDelete(Integer id) {
        UserDto userDto = this.userRepository.findByIdAndIsActive(id,true)
                .map(UserDto::new)
                .orElseThrow(UserNotFoundException::new);
        this.userRepository.deleteById(id);
        return userDto;
    }

    private User update(User user, UserDto userDto){
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setRoles(findRoles(userDto.getRoles()));
        return user;
    }

}
