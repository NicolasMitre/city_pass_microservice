package net.avalith.city_pass.repositories;

import net.avalith.city_pass.dto.ExcursionDto;
import net.avalith.city_pass.models.City;
import net.avalith.city_pass.models.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion,Integer> {
    Optional<Excursion> findByIdAndIsActive(Integer idExcursion, Boolean value);

    @Query(value = "SELECT * from excursion where is_active = :status", nativeQuery = true)
    List<ExcursionDto> findAllByStatus(@Param("status") Boolean status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cities SET is_active = false where cities_id = ?1", nativeQuery = true)
    void logicalDelete(Integer idExcursion);
}
