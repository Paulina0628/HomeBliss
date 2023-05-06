package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.ProductPostDTO;
import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.ProductState;
import co.edu.uniquindio.homebliss.model.State;

import java.util.List;

public interface ProductService {

    int createProduct(ProductPostDTO productPostDTO) throws Exception;

    ProductGetDTO updateProduct(int productCode, ProductPostDTO productPostDTO) throws Exception;

    ProductGetDTO updateStock(int productCode, int stock) throws Exception;

    ProductGetDTO updateState(int productCode, ProductState state) throws Exception;

    Product update (Product product);

    void deleteProduct(int productCode) throws Exception;

    Product getProduct(int productCode) throws Exception;

    ProductGetDTO getProductDTO(int productCode) throws Exception;

    List<ProductGetDTO> getUserProducts(int clientCode) throws Exception;

    List<ProductGetDTO> getProductsByCategory(Category category);

    List<ProductGetDTO> getProductsByState(State state);

    List<ProductGetDTO> getFavoritesProducts(int clientCode) throws Exception;

    List<ProductGetDTO> getProductsByName(String name);

    List<ProductGetDTO> getProductsByPrice(float minPrice, float maxPrice);

    List<ProductGetDTO> getProducts ();

}
