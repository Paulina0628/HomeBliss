package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.ProductByClientGetDTO;
import co.edu.uniquindio.homebliss.dto.ProductByClientSelectDTO;
import co.edu.uniquindio.homebliss.dto.ProductPostDTO;
import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.model.*;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ClientService clientService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ClientService clientService) {
        this.productRepository = productRepository;
        this.clientService = clientService;
    }

    @Override
    public int createProduct(ProductPostDTO productPostDTO) throws Exception {

        Product product = toProduct(null, productPostDTO);

        product.setState(ProductState.INACTIVO);
        product.setCreated_date(LocalDateTime.now());
        product.setLimit_date(LocalDateTime.now().plusDays(60));

        return productRepository.save(product).getId();
    }

    @Override
    public ProductGetDTO updateProduct(int productCode, ProductPostDTO productPostDTO) throws Exception {

        if (validateProduct(productCode)) {
            throw new Exception("El código " + productCode + " no está asociado a ningún producto");
        }

        Product product = toProduct(productRepository.getReferenceById(productCode), productPostDTO);
        product.setId(productCode);

        return toProductDTO(productRepository.save(product));
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProductGetDTO updateStock(int productCode, int stock) throws Exception {
        if (validateProduct(productCode)) {
            throw new Exception("El código " + productCode + " no está asociado a ningún producto");
        }

        Product product = getProduct(productCode);
        product.setStock(stock);

        return toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductGetDTO updateState(int productCode, ProductState state) throws Exception {
        if (validateProduct(productCode)) {
            throw new Exception("El código " + productCode + " no está asociado a ningún producto");
        }

        Product product = getProduct(productCode);
        product.setState(state);

        return toProductDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(int productCode) throws Exception {
        if (validateProduct(productCode)) {
            throw new Exception("El código " + productCode + " no está asociado a ningún producto");
        }

        Product product = getProduct(productCode);
        product.setState(ProductState.ELIMINADO);
        productRepository.save(product);

    }

    @Override
    public ProductGetDTO getProductDTO(int productCode) throws Exception {
        return toProductDTO(getProduct(productCode));
    }

    @Transactional(readOnly = true)
    public Product getProduct(int productCode) throws Exception {
        Optional<Product> product = productRepository.findById(productCode);

        if (product.isEmpty()) {
            throw new Exception("El código " + productCode + " no está asociado a ningún producto");
        }
        return product.get();
    }

    @Transactional(readOnly = true)
    public List<ProductGetDTO> getUserProducts(int clientCode) throws Exception {

        List<Product> list = productRepository.findAllBySeller(clientCode);
        List<ProductGetDTO> answer = new ArrayList<>();


        for (Product p : list) {
            answer.add(toProductDTO(p));
        }

        return answer;
    }

    @Override
    public List<ProductGetDTO> getProductsByCategory(Category category) {

        List<Product> list = productRepository.findByCategoriesIn(List.of(category));
        List<ProductGetDTO> answer = new ArrayList<>();

        for (Product p : list) {
            if (p.getState() == ProductState.ACTIVO) {
                answer.add(toProductDTO(p));
            }
        }

        return answer;
    }

    @Override
    public List<ProductGetDTO> getProductsByCategory(String category, String query) {
        if (query == null || query.isEmpty()) query = "";

        List<Product> list = query.isEmpty() ? productRepository.findAprobadas() : productRepository.findAprobadasByName(query);
        if (!category.equalsIgnoreCase("ALL")) {
            Category categoryItem = Category.valueOf(category.toUpperCase());
            list.removeIf(p -> !p.getCategories().contains(categoryItem));
        }

        List<ProductGetDTO> answer = new ArrayList<>();
        for (Product p : list) {
            if (p.getState() == ProductState.ACTIVO) {
                answer.add(toProductDTO(p));
            }
        }
        return answer;
    }

    @Override
    public List<ProductGetDTO> getProductsByState(State state) {
        List<Product> list = productRepository.findAllByState(state);
        List<ProductGetDTO> answer = new ArrayList<>();

        for (Product p : list) {
            answer.add(toProductDTO(p));
        }

        return answer;
    }

    @Override
    public ProductByClientGetDTO getProductByClient(int clientCode, int productCode) {
        Product product = productRepository.getReferenceById(productCode);
        List<Product> list = productRepository.findAllByClientFavorite(clientCode);
        list.removeIf(p -> p.getId() != productCode);
        return toProductByClientDTO(product, list.size() > 0);
    }

    @Override
    public List<ProductGetDTO> getProductsByClient(int clientCode) {
        List<Product> list = productRepository.findAllBySeller(clientCode);
        List<ProductGetDTO> answer = new ArrayList<>();
        for (Product p : list) {
            if (p.getState() != ProductState.ELIMINADO) {
                answer.add(toProductDTO(p));
            }
        }
        return answer;
    }

    @Override
    public List<ProductGetDTO> getFavoritesProducts(int clientCode) throws Exception {

        List<Product> list = productRepository.findAllByClientFavorite(clientCode);
        List<ProductGetDTO> answer = new ArrayList<>();

        for (Product p : list) {
            if (p.getState() == ProductState.ACTIVO) {
                answer.add(toProductDTO(p));
            }
        }

        return answer;
    }

    @Override
    public List<ProductGetDTO> getProductsByName(String name) {
        List<Product> list = productRepository.findAllByName(name);
        List<ProductGetDTO> answer = new ArrayList<>();

        for (Product p : list) {
            if (p.getState() == ProductState.ACTIVO) {
                answer.add(toProductDTO(p));
            }
        }

        return answer;
    }

    @Override
    public List<ProductGetDTO> getProductsByPrice(float minPrice, float maxPrice) {
        List<Product> list = productRepository.findAllByPrice(minPrice, maxPrice);
        List<ProductGetDTO> answer = new ArrayList<>();

        for (Product p : list) {
            answer.add(toProductDTO(p));
        }

        return answer;
    }

    @Override
    public List<ProductGetDTO> getProducts() {
        List<Product> list = productRepository.findAll();
        List<ProductGetDTO> answer = new ArrayList<>();
        for (Product p : list) {
            if (p.getState() == ProductState.ACTIVO) {
                answer.add(toProductDTO(p));
            }
        }
        return answer;
    }

    public boolean validateProduct(int productCode) throws Exception {
        Optional<Product> product = productRepository.findById(productCode);
        return product.isEmpty();
    }

    public ProductByClientGetDTO toProductByClientDTO(Product product, boolean favorite) {
        return new ProductByClientGetDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getState(),
                product.getLimit_date(),
                product.getSeller().getId(),
                product.getImages(),
                product.getCategories(),
                product.getQuestions(),
                favorite);
    }

    public ProductGetDTO toProductDTO(Product product) {
        return new ProductGetDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getState(),
                product.getLimit_date(),
                product.getSeller().getId(),
                product.getImages(),
                product.getCategories(),
                product.getQuestions());
    }

    public Product toProduct(Product product, ProductPostDTO productPostDTO) throws Exception {

        if (product == null) product = new Product();

        product.setName(productPostDTO.getName());
        product.setDescription(productPostDTO.getDescription());
        product.setStock(productPostDTO.getStock());
        product.setPrice(productPostDTO.getPrice());
        product.setSeller(clientService.getClient(productPostDTO.getSellerCode()));
        product.setImages(productPostDTO.getImages());
        product.setCategories(productPostDTO.getCategories());

        return product;
    }

}
