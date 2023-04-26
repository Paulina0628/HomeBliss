package co.edu.uniquindio.homebliss.repositories;

import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query("select p from Purchase p where :clientCode = p.client.id")
    List<Purchase> findAllByUser(int clientCode);
}
