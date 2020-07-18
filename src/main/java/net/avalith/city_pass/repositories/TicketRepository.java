package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    @Query("SELECT t from Ticket t where t.purchase.id = ?1")
    List<Ticket> getAllTicketsByPurchaseId(Integer idPurchase);

    @Query("SELECT coalesce(sum(t.quantity),0) FROM Ticket t WHERE t.theaterPlay.idTheaterPlay = ?1 ")
    public Integer sumById(Integer id);
}
