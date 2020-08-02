package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.TheaterPlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TheaterPlayRepository extends JpaRepository<TheaterPlay, Integer> {
    @Query(value = "Select t from TheaterPlay t where t.city.name = ?1 and is_active = ?2")
    List<TheaterPlay> findByCityNameAndIsActive(String cityName,Boolean value);

    Optional<TheaterPlay> findByIdTheaterPlayAndIsActive(Integer id, Boolean b);

    Optional<TheaterPlay> findByNameAndIsActive(String theaterPlayName, Boolean status);

    List<TheaterPlay> findAllByIsActive(Boolean status);
}
