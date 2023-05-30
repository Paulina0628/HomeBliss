package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.ProductModeratorGetDTO;
import co.edu.uniquindio.homebliss.dto.ProductModeratorDTO;
import co.edu.uniquindio.homebliss.model.State;

import java.util.List;

public interface ProductModeratorService {

    int createLog(ProductModeratorDTO productModeratorDTO) throws Exception;

    List<ProductModeratorGetDTO> getProductsByState(State state);

}
