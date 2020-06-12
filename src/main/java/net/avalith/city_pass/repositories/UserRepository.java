package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET is_active = false where id_user = ?1", nativeQuery = true)
    void deleteById(Integer id);

    List<User> findAllByIsActive(Boolean status);

    Optional<User> findByIdAndIsActive(Integer id, Boolean b);
}
