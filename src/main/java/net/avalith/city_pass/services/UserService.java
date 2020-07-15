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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getAll() {
        return this.userRepository.findAllByIsActive(Boolean.TRUE);
    }

    public User getById(Integer id) {
        return userRepository.findByIdUserAndIsActive(id,Boolean.TRUE)
                .orElseThrow(UserNotFoundException::new);
    }

    public User createUser(UserDto userDto) {

        User user = User.builder().name(userDto.getName())
                .username(userDto.getUsername())
                .roles(findRoles(userDto.getRoles()))
                .build();
        return userRepository.save(user);
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

    public User updateUser(Integer idUser, UserDto userDto) {
        return this.userRepository.findByIdUserAndIsActive(idUser, Boolean.TRUE)
                .map(user -> update(user,userDto))
                        .map(user -> this.userRepository.save(user))
                        .orElseThrow(UserNotFoundException::new);
    }

    public User logicDelete(Integer id) {
        User user= this.userRepository.findByIdUserAndIsActive(id,Boolean.TRUE)
                .orElseThrow(UserNotFoundException::new);
        user.setIsActive(Boolean.FALSE);
        return userRepository.save(user);
    }

    private User update(User user, UserDto userDto){
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setRoles(findRoles(userDto.getRoles()));
        return user;
    }

}
