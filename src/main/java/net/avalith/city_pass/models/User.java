package net.avalith.city_pass.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Set;

@Data
@Builder
@Entity
public class User {
    private Integer id;
    private String username;
    private String name;
    private Set<Role> roles;
}