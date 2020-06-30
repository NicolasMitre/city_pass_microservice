package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
