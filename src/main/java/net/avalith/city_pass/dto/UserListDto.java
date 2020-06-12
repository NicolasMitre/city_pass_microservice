package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.User;

import java.util.List;
import java.util.stream.Collectors;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {
    private List<UserDto> listDto;

    public static UserListDto fromListDto(List<User> list) {
        return UserListDto.builder()
                .listDto(list.stream()
                        .map(UserDto::new)
                        .collect(Collectors.toList()))
                .build();
    }
}
