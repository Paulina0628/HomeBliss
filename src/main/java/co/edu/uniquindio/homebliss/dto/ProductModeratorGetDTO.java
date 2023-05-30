package co.edu.uniquindio.homebliss.dto;

import co.edu.uniquindio.homebliss.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductModeratorGetDTO {

    private int id;

    private String name;

    private String description;

    private State state;

    private String reason;

    private List<String> images;

    public static ProductModeratorGetDTO build(ProductSelectDTO selector) {
        Product product = selector.getProduct();
        return new ProductModeratorGetDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                selector.getProductModerator() == null ? null : selector.getProductModerator().getState(),
                selector.getProductModerator() == null ? null : selector.getProductModerator().getReason(),
                product.getImages()
        );
    }

}
