package net.avalith.city_pass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.Role;
import net.avalith.city_pass.models.User;

import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Invalid username")
    private String username;

    @NotBlank(message = "Invalid name")
    private String name;

    private List<String> roles;



    public UserDto (User user) {
        Set<Role> roleSet = user.getRoles();
        List<String> r = new ArrayList<>();
        System.out.println("Roleset :" + roleSet);
        for (Role rol : roleSet) {
            r.add(rol.getName());
        }
        this.username = user.getUsername();
        this.name =  user.getName();
        roles = r;
    }
}
