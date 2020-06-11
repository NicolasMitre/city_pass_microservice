package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    Optional<City> findByIdAndIsActive(Integer idCity, Boolean aTrue);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cities SET is_active = false where id_city = ?1", nativeQuery = true)
    void logicalDelete(Integer idCity);

    List<City> findAllByIsActive(Boolean status);

    Optional<City> findByNameAndIsActive(String cityName, Boolean aTrue);
}
