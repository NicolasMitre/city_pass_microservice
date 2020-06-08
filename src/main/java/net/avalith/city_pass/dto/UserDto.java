package net.avalith.city_pass.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.User;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Invalid username")
    private String username;

    @NotBlank(message = "Invalid name")
    private String name;

    public UserDto fromUser(User user) {
        return new UserDto(user.getName(), user.getUsername());
    }
}
