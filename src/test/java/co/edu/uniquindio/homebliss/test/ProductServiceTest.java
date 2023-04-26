package co.edu.uniquindio.homebliss.test;

import co.edu.uniquindio.homebliss.dto.ClientGetDTO;
import co.edu.uniquindio.homebliss.dto.ClientPostDTO;
import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.dto.ProductPostDTO;
import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.model.ProductState;
import co.edu.uniquindio.homebliss.model.State;
import co.edu.uniquindio.homebliss.model.UserState;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.jdbc.Sql;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach()
    public void init() throws Exception {
        this.productRepository.deleteAll();
        this.clientRepository.deleteAll();
    }


    @Test
    @Order(1)
    public void createProductTest() throws Exception {

        ClientPostDTO clientPostDTO = new ClientPostDTO("Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "34331222", UserState.ACTIVO);

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int sellerCode = clientService.createClient(clientPostDTO);

        //Se crea la colección de imágenes para el producto.
        List<String> images = new ArrayList<>();
        images.add("http://www.google.com/images/imagenasus.png");
        images.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductPostDTO productPostDTO = new ProductPostDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                sellerCode,
                images,
                List.of(Category.TECNOLOGIA)
        );

        //Se llama el servicio para crear el producto
        int productCode = productService.createProduct(productPostDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        Assertions.assertEquals(1, productCode);
    }

    @Test
    @Order(2)
    public void deleteProductTest() throws Exception{

        ClientPostDTO clientPostDTO = new ClientPostDTO("Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "34331222", UserState.ACTIVO);

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int sellerCode = clientService.createClient(clientPostDTO);

        //Se crea la colección de imágenes para el producto.
        List<String> images = new ArrayList<>();
        images.add("http://www.google.com/images/imagenasus.png");
        images.add("http://www.google.com/images/imagenasus_original.png");

        //Para eliminar el producto primero se debe crear
        ProductPostDTO productPostDTO = new ProductPostDTO(
                "Sandwichera 4",
                "Sanduchera de 4 slots para ricos sandwiches",
                6,
                60000,
                sellerCode,
                images,
                List.of(Category.TECNOLOGIA, Category.HOGAR));

        //Se llama el servicio para crear el producto
        int code = productService.createProduct(productPostDTO);

        //Una vez creado, lo borramos
        productService.deleteProduct(code);

        //Si intentamos buscar el producto con el código del producto borrado se comprueba que está en estado ELIMINADO
        Assertions.assertEquals(ProductState.ELIMINADO, productService.getProduct(code).getState());
    }

    @Test
    @Order(2)
    public void updateProductTest() throws Exception{

        ClientPostDTO clientPostDTO = new ClientPostDTO("Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "34331222", UserState.ACTIVO);

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int sellerCode = clientService.createClient(clientPostDTO);

        //Se crea la colección de imágenes para el producto.
        List<String> images = new ArrayList<>();
        images.add("http://www.google.com/images/imagenasus.png");
        images.add("http://www.google.com/images/imagenasus_original.png");

        //Para actualizar el producto primero se debe crear
        ProductPostDTO productPostDTO = new ProductPostDTO(
                "Sandwichera 4",
                "Sanduchera de 4 slots para ricos sandwiches",
                6,
                60000,
                sellerCode,
                images,
                List.of(Category.TECNOLOGIA, Category.HOGAR));

        //Se llama el servicio para crear el producto
        int code = productService.createProduct(productPostDTO);

        //El servicio de actualizar nos retorna el producto
        ProductGetDTO updatedProduct = productService.updateProduct(code, new ProductPostDTO(
                "Sandwichera 4",
                "Sanduchera de 4 slots para ricos sandwiches",
                6,
                100000,
                sellerCode,
                images,
                List.of(Category.TECNOLOGIA, Category.HOGAR)));

        //Se comprueba que ahora el precio del producto no es el que se usó cuando se creó inicialmente
        Assertions.assertNotEquals(60000, updatedProduct.getPrice());

    }

    @Test
    @Order(2)
    public void updateStockProductTest() throws Exception{

        ClientPostDTO clientPostDTO = new ClientPostDTO("Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "34331222", UserState.ACTIVO);

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int sellerCode = clientService.createClient(clientPostDTO);

        //Se crea la colección de imágenes para el producto.
        List<String> images = new ArrayList<>();
        images.add("http://www.google.com/images/imagenasus.png");
        images.add("http://www.google.com/images/imagenasus_original.png");

        //Para eliminar el producto primero se debe crear
        ProductPostDTO productPostDTO = new ProductPostDTO(
                "Sandwichera 4",
                "Sanduchera de 4 slots para ricos sandwiches",
                6,
                60000,
                sellerCode,
                images,
                List.of(Category.TECNOLOGIA, Category.HOGAR));

        //Se llama el servicio para crear el producto
        int code = productService.createProduct(productPostDTO);

        //El servicio de actualizar nos retorna el producto
        ProductGetDTO updatedProduct = productService.updateStock(code, 14);

        //Se comprueba que ahora el stock del producto es el que se actualizó
        Assertions.assertEquals(14, updatedProduct.getStock());

    }

    @Test
    @Order(2)
    public void updateStateProductTest() throws Exception{

        ClientPostDTO clientPostDTO = new ClientPostDTO("Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "34331222", UserState.ACTIVO);

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int sellerCode = clientService.createClient(clientPostDTO);

        //Se crea la colección de imágenes para el producto.
        List<String> images = new ArrayList<>();
        images.add("http://www.google.com/images/imagenasus.png");
        images.add("http://www.google.com/images/imagenasus_original.png");

        //Para eliminar el producto primero se debe crear
        ProductPostDTO productPostDTO = new ProductPostDTO(
                "Sandwichera 4",
                "Sanduchera de 4 slots para ricos sandwiches",
                6,
                60000,
                sellerCode,
                images,
                List.of(Category.TECNOLOGIA, Category.HOGAR));

        //Se llama el servicio para crear el producto
        int code = productService.createProduct(productPostDTO);

        //El servicio de actualizar nos retorna el producto
        ProductGetDTO updatedProduct = productService.updateState(code, ProductState.ACTIVO);

        //Se comprueba que el estado que al momento de crearse es INACTIVO se halla actualizado correctamente a ACTIVO
        Assertions.assertEquals(ProductState.ACTIVO, updatedProduct.getState());

    }

    @Test
    @Order(2)
    public void getUserProductsTest() throws Exception{

        ClientPostDTO clientPostDTO = new ClientPostDTO("Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "34331222", UserState.ACTIVO);

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int sellerCode = clientService.createClient(clientPostDTO);

        //Se crea la colección de imágenes para el producto.
        List<String> images = new ArrayList<>();
        images.add("http://www.google.com/images/imagenasus.png");
        images.add("http://www.google.com/images/imagenasus_original.png");

        //Para actualizar el producto primero se debe crear
        ProductPostDTO productPostDTO = new ProductPostDTO(
                "Sandwichera 4",
                "Sanduchera de 4 slots para ricos sandwiches",
                6,
                60000,
                sellerCode,
                images,
                List.of(Category.TECNOLOGIA, Category.HOGAR));

        //Se llama el servicio para crear el producto
        int code = productService.createProduct(productPostDTO);

        //Se llama el servicio para obtener los productos del cliente con código 1
        List<ProductGetDTO> products = productService.getUserProducts(sellerCode);

        //Se imprimen los productos
        System.out.println("NOMBRE: " + products.get(0).getName());

    }

    @Test
    @Order(2)
    public void getProductsByStateTest() throws Exception{

        //Se llama el servicio para obtener los productos de estado SINREVISAR
        List<ProductGetDTO> products = productService.getProductsByState(State.SINREVISAR);

        //Se imprimen los productos
        System.out.println(products);

    }

    @Test
    @Order(2)
    public void getFavoritesProductsTest() throws Exception{

        //Se llama el servicio para obtener los productos favoritos del cliente 1
        List<ProductGetDTO> products = productService.getFavoritesProducts(1);

        //Se imprimen los productos
        System.out.println(products);

    }

    @Test
    @Order(2)
    public void getProductsByNameTest() throws Exception{

        //Se llama el servicio para obtener los productos favoritos del cliente 1
        List<ProductGetDTO> products = productService.getProductsByName("Trituradora");

        //Se imprimen los productos
        System.out.println(products);

    }

    @Test
    @Order(2)
    public void getProductsByPriceTest() throws Exception{

        ClientPostDTO clientPostDTO = new ClientPostDTO("Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "34331222", UserState.ACTIVO);

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int sellerCode = clientService.createClient(clientPostDTO);

        //Se crea la colección de imágenes para el producto.
        List<String> images = new ArrayList<>();
        images.add("http://www.google.com/images/imagenasus.png");
        images.add("http://www.google.com/images/imagenasus_original.png");

        //Para actualizar el producto primero se debe crear
        ProductPostDTO productPostDTO = new ProductPostDTO(
                "Sandwichera 4",
                "Sanduchera de 4 slots para ricos sandwiches",
                6,
                60_000,
                sellerCode,
                images,
                List.of(Category.TECNOLOGIA, Category.HOGAR));

        ProductPostDTO productPostDTO2 = new ProductPostDTO(
                "Sandwichera 8",
                "Sanduchera de 8 slots para ricos sandwiches",
                3,
                170_000,
                sellerCode,
                images,
                List.of(Category.HOGAR));

        //Se llama el servicio para crear los productos
        int code = productService.createProduct(productPostDTO);
        int code2 = productService.createProduct(productPostDTO2);

        //Se llama el servicio para obtener los productos favoritos del cliente 1
        List<ProductGetDTO> products = productService.getProductsByPrice(30000, 100000);

        //Se imprimen los productos
        Assertions.assertEquals(1, products.size());

    }

}
