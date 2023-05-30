package co.edu.uniquindio.homebliss.dto;

import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.ProductModerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductSelectDTO {

    private Product product;
    private ProductModerator productModerator;

}
