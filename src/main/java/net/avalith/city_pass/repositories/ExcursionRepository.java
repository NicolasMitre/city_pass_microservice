package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion,Integer> {
    Excursion findByIdAndIsActive(Integer idExcursion, Boolean value);

    @Query(value = "SELECT * from excursion where is_active = :status", nativeQuery = true)
    List<Excursion> findAllByStatus(@Param("status") Boolean status);

    @Query(value = "from Excursion e where e.city.name = ?1 and is_active = ?2")
    List<Excursion> findByCityNameAndStatus(String cityName, Boolean status);

    Excursion findByNameAndIsActive(String name, Boolean status);
}
