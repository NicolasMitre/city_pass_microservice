package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.User;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Invalid username")
    private String username;

    @NotBlank(message = "Invalid name")
    private String name;

    private List<RoleDto> roles;

    public UserDto fromUser(User user) {
        return new UserDto(user.getName(), user.getUsername(),user.getRoles());
    }
}
