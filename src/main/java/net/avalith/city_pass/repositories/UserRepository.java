package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAllByIsActive(Boolean status);

    Optional<User> findByIdUserAndIsActive(Integer id, Boolean b);
}
