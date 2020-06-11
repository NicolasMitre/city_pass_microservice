package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.models.User;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "name is mandatory")
    private String name;

    private List<String> roles;



    public UserDto (User user) {

        this.username = user.getUsername();
        this.name =  user.getName();
        roles = user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
    }
}
