package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.Role;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    @NotBlank(message = "Invalid name")
    private String name;

    public void fromRole(Role role){
        setName(role.getName());
    }
}
