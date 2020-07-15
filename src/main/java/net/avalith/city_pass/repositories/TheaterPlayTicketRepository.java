package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.TheaterPlayTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterPlayTicketRepository extends JpaRepository<TheaterPlayTicket,Integer> {
}
