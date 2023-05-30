package co.edu.uniquindio.homebliss.repositories;

import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where :clientCode = p.seller.id")
    List<Product> findAllBySeller(int clientCode);

    @Query("select p from Product p where p.categories in :categories")
    List<Product> findByCategoriesIn(List<Category> categories);

    @Query("select p from Product p where p.price > :minPrice and p.price < :maxPrice")
    List<Product> findAllByPrice(float minPrice, float maxPrice);

    @Query("select p from Product p inner join ProductModerator pM join p.clientFavorite c where pM.state = 1 and :clientCode = c.id")
    List<Product> findAllByClientFavorite(int clientCode);

    @Query("select p from Product p where p.name like concat( '%', :name, '%' )")
    List<Product> findAllByName(String name);

    @Query("select p from Product p inner join ProductModerator pM on p.id = pM.product.id where pM.state = 1")
    List<Product> findAprobadas();

    @Query("select p from Product p inner join ProductModerator pM on p.id = pM.product.id where pM.state = 1 and p.name like concat( '%', :query, '%' )")
    List<Product> findAprobadasByName(String query);

    @Query("select p from Product p join ProductModerator pM on p.id = pM.product.id where :state = pM.state")
    List<Product> findAllByState(State state);

}
