package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
