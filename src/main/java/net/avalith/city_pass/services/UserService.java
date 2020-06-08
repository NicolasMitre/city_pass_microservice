package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.CityDto;
import net.avalith.city_pass.dto.UserDto;
import net.avalith.city_pass.exceptions.CityNotFoundException;
import net.avalith.city_pass.exceptions.UserNotFoundException;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.User;
import net.avalith.city_pass.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user.getName(),user.getUsername()))
                .collect(Collectors.toList());
    }

    public UserDto getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        UserDto userDto = new UserDto();
        userDto.fromUser(user);
        return userDto;
    }
}
