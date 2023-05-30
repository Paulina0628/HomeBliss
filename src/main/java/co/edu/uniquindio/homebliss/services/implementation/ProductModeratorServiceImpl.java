package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.ProductModeratorGetDTO;
import co.edu.uniquindio.homebliss.dto.ProductModeratorDTO;
import co.edu.uniquindio.homebliss.dto.ProductSelectDTO;
import co.edu.uniquindio.homebliss.model.*;
import co.edu.uniquindio.homebliss.repositories.ModeratorRepository;
import co.edu.uniquindio.homebliss.repositories.ProductModeratorRepository;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.services.interfaces.ProductModeratorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductModeratorServiceImpl implements ProductModeratorService {

    @Autowired
    private ProductModeratorRepository productModeratorRepository;

    @Autowired
    private ModeratorRepository moderatorRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public int createLog(ProductModeratorDTO dto) throws Exception {

        Moderator moderator = moderatorRepository.getReferenceById(dto.getModeratorCode());
        Product product = productRepository.getReferenceById(dto.getProductCode());
        product.setState(ProductState.ACTIVO);
        productRepository.save(product);

        ProductModerator newModerator = new ProductModerator();
        newModerator.setDate(Timestamp.from(Instant.now()));
        newModerator.setReason(dto.getReason());
        newModerator.setState(State.valueOf(dto.getState()));
        newModerator.setModerator(moderator);
        newModerator.setProduct(product);
        productModeratorRepository.save(newModerator);
        return newModerator.getId();
    }

    @Override
    public List<ProductModeratorGetDTO> getProductsByState(State state) {
        List<ProductSelectDTO> list = state == State.SINREVISAR || state == null ? productModeratorRepository.findAllBySinRevisar() : productModeratorRepository.findAllByState(state);
        List<ProductModeratorGetDTO> result = new ArrayList<>();
        for (ProductSelectDTO selector: list) {
            result.add(ProductModeratorGetDTO.build(selector));
        }
        return result;
    }

}
