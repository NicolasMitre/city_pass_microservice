package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.TheaterPlay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterPlayRepository extends JpaRepository<TheaterPlay, Integer> {
    List<TheaterPlay> findAllByisActive(Boolean status);
}
