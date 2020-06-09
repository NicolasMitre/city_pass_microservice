package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET is_active = false where id_user = ?1", nativeQuery = true)
    void deleteUser(Integer id);

    @Query(value = "SELECT * from user where is_active =:status", nativeQuery = true)
    List<User> findAllStatus(@Param(value = "status") Boolean b);

    Optional<User> findByIdAndIsActive(Integer id, Boolean b);
}
