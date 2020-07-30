package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.TheaterPlayTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterPlayTicketRepository extends JpaRepository<TheaterPlayTicket,Integer> {
    @Query("SELECT coalesce(sum(t.quantity),0) FROM TheaterPlayTicket t WHERE t.theaterPlay.idTheaterPlay = ?1 AND" +
            " t.ticketStatus <> 'DENIED'" )
    Integer  sumTheaterPlayTicketById(Integer idTheaterPlay);
}
