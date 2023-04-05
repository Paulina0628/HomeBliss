package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.ProductDTO;
import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.State;

import java.util.List;

public interface ProductService {

    int createProduct(ProductDTO productDTO) throws Exception;

    int updateProduct(int productCode, ProductDTO productDTO);

    int updateStock(int productCode, int stock);

    int updateState(int productCode, State state);

    int deleteProduct(int productCode);

    ProductGetDTO getProduct(int productCode);

    List<ProductGetDTO> getUserProducts(int userCode);

    List<ProductGetDTO> getProductsByCategory(Category category);

    List<ProductGetDTO> getProductsByState(State state);

    List<ProductGetDTO> getFavoritesProducts(int userCode);

    List<ProductGetDTO> getProductsByName(String name);

    List<ProductGetDTO> getProductsByPrice(float minPrice, float maxPrice);

}
