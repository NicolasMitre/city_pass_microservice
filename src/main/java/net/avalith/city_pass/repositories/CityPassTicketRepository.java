package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.CityPassTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityPassTicketRepository extends JpaRepository<CityPassTicket,Integer> {
}
