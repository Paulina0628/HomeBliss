package co.edu.uniquindio.homebliss.repositories;

import co.edu.uniquindio.homebliss.dto.ProductSelectDTO;
import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.ProductModerator;
import co.edu.uniquindio.homebliss.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductModeratorRepository extends JpaRepository<ProductModerator, Integer> {

    @Query("select new co.edu.uniquindio.homebliss.dto.ProductSelectDTO(p, pM) from Product p left join ProductModerator pM on p.id = pM.product.id where (:state = pM.state)")
    List<ProductSelectDTO> findAllByState(State state);

    @Query("select new co.edu.uniquindio.homebliss.dto.ProductSelectDTO(p, pM) from Product p left join ProductModerator pM on p.id = pM.product.id where pM.state = 0 OR pM.state is null")
    List<ProductSelectDTO> findAllBySinRevisar();

}
