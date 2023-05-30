package co.edu.uniquindio.homebliss.dto;

import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.ProductModerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductByClientSelectDTO {

    private Product product;
    private boolean favorite;

}
