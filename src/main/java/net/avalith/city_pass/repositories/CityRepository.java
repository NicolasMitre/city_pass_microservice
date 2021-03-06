package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    Optional<City> findByIdCityAndIsActive(Integer idCity, Boolean aTrue);

    List<City> findAllByIsActive(Boolean status);

    Optional<City> findByNameAndIsActive(String cityName, Boolean aTrue);
}
