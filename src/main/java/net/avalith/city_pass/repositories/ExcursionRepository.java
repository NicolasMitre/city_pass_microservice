package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion,Integer> {
    Optional<Excursion> findByIdAndIsActive(Integer idExcursion, Boolean value);

    Optional<Excursion> findByNameAndIsActive(String name, Boolean status);

    List<Excursion> findAllByIsActive(Boolean status);

    @Query(value = "Select e from Excursion e where e.city.name = ?1 and is_active = ?2")
    List<Excursion> findByCityNameAndStatus(String cityName, Boolean status);
}
