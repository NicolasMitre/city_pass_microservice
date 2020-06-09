package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.CityPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CityPassRepository extends JpaRepository<CityPass, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE city_pass SET is_active = false where id_city_pass = ?1", nativeQuery = true)
    void logicDelete(Integer idCityPass);

    Optional<CityPass> findByIdAndIsActive(Integer idCityPass, boolean b);

    @Query(value = "SELECT * from city_pass where is_active = :status", nativeQuery = true)
    List<CityPass> findAllByStatus(@Param("status") Boolean b);
}
