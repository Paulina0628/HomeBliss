package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.ProductDTO;
import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.State;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ClientService clientService;

    @Override
    public int createProduct(ProductDTO productDTO) throws Exception {
        Product product = new Product();
        product.setName( productDTO.getName() );
        product.setDescription( productDTO.getDescription() );
        product.setStock( productDTO.getStock() );
        product.setPrice( productDTO.getPrice() );
        product.setSeller( clientService.getClient( productDTO.getSellerCode() ) );
        product.setImages(productDTO.getImages() );
        product.setCategories(productDTO.getCategories());
        product.setIsActive( Activo.INACTIVO );
        product.setCreated_date(LocalDateTime.now());
        product.setLimit_date(LocalDateTime.now().plusDays(60));

        return productRepository.save( product ).getId();
    }

    @Override
    public int updateProduct(int productCode, ProductDTO productDTO) {
        return 0;
    }

    @Override
    public int deleteProduct(int productCode) {
        return 0;
    }

    @Override
    public ProductGetDTO getProduct(int productCode) {
        return null;
    }

    @Override
    public List<ProductGetDTO> getUserProducts(int userCode) {

        List<Product> list = productRepository.listarProductosUsuario(userCode);
        List<ProductGetDTO> answer = new ArrayList<>();

        for(Product p : list){
            answer.add( toDTO(p) );
        }

        return answer;
    }

    @Override
    public List<ProductGetDTO> getProductsByCategory(Category category) {
        return null;
    }

    @Override
    public List<ProductGetDTO> getProductsByState(State state) {
        return null;
    }

    @Override
    public List<ProductGetDTO> getFavoritesProducts(int userCode) {
        return null;
    }

    @Override
    public List<ProductGetDTO> getProductsByName(String name) {
        List<Product> list = productRepository.listarProductosNombre(name);
        List<ProductGetDTO> answer = new ArrayList<>();

        for(Product p : list){
            answer.add(toDTO(p));
        }

        return answer;
    }

    @Override
    public List<ProductGetDTO> getProductsByPrice(float minPrice, float maxPrice) {
        return null;
    }
    private ProductGetDTO toDTO(Product product){

        ProductGetDTO productGetDTO = new ProductGetDTO(
                product.getId(),
                product.getIsActive(),
                product.getLimit_date(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getPrice(),
                product.getSeller().getCodigo(),
                product.getImages(),
                product.getCategories()
        );

        return productGetDTO;
    }

}
