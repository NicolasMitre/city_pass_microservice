package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.CityPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityPassRepository extends JpaRepository<CityPass, Integer> {
}
