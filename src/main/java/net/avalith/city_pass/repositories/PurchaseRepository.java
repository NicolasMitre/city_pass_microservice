package net.avalith.city_pass.repositories;

import net.avalith.city_pass.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
    @Query("Select p from Purchase p where p.user.idUser = ?1")
    List<Purchase> findAllByIdClient(Integer idClient);
}

