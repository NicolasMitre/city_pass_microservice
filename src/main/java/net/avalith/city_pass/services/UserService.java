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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<UserDto> getAll() {
        return this.userRepository.findAllStatus(true)
                .stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toList());
    }

    public UserDto getById(Integer id) {
        User user = userRepository.findByIdAndIsActive(id,true)
                .orElseThrow(UserNotFoundException::new);
        UserDto userDto = new UserDto(user);
        return userDto;
    }

    public URI createUser(UserDto userDto) {

        User user = User.builder().name(userDto.getName())
                                  .username(userDto.getUsername())
                                  .roles(findRoles(userDto.getRoles()))
                                  .isActive(true)
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

    public User updateUser(Integer idUser, UserDto userDto) {
        User user = this.userRepository.findByIdAndIsActive(idUser, true)
                                       .orElseThrow(UserNotFoundException::new);
        update(user,userDto);
        user = this.userRepository.save(user);
        return User.builder()
                .name(user.getName())
                .username(user.getUsername())
                .roles(user.getRoles())
                .isActive(user.getIsActive())
                .build();
    }

    private void update(User user, UserDto userDto){
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setRoles(findRoles(userDto.getRoles()));
    }

    public void logicDelete(Integer id) {
        User user = this.userRepository.findByIdAndIsActive(id,true)
                .orElseThrow(UserNotFoundException::new);
        this.userRepository.deleteUser(id);
    }
}
