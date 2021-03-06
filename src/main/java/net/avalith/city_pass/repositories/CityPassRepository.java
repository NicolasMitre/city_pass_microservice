package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.CityPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityPassRepository extends JpaRepository<CityPass, Integer> {

    Optional<CityPass> findByIdCityPassAndIsActive(Integer idCityPass, Boolean status);

    List<CityPass> findAllByIsActive(Boolean status);
}
