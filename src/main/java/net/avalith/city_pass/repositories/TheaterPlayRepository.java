package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.TheaterPlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TheaterPlayRepository extends JpaRepository<TheaterPlay, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET is_active = false where id_user = ?1", nativeQuery = true)
    void deleteById(Integer id);
    List<TheaterPlay> findAllByisActive(Boolean status);

    @Transactional
    @Query(value ="SELECT * FROM theater_play t inner join cities c on c.id_city = t.cities_id WHERE t.is_active = ?2 AND c.name =?1 ",nativeQuery = true)
    Optional<List<TheaterPlay>> findByCityNameAndIsActive(String cityName,Boolean value);

    Optional<TheaterPlay> findByIdAndIsActive(Integer id, Boolean b);

    Optional<TheaterPlay> findByNameAndIsActive(String theaterPlayName, Boolean aTrue);
}
