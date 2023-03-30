package co.edu.uniquindio.homebliss.repositories;

import co.edu.uniquindio.homebliss.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.seller.id = :userCode")
    List<Product> getUserProducts(int userCode);

    @Query("select p from Product p where p.name like concat( '%', :name, '%' ) and p.isActive = TRUE ")
    List<Product> getProductByName(String name);

}
