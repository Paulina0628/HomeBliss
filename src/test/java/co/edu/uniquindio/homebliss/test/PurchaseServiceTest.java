package co.edu.uniquindio.homebliss.test;

import co.edu.uniquindio.homebliss.dto.ClientPostDTO;
import co.edu.uniquindio.homebliss.dto.ProductPostDTO;
import co.edu.uniquindio.homebliss.dto.PurchasePostDTO;
import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.model.UserState;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import co.edu.uniquindio.homebliss.services.interfaces.PurchaseService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class PurchaseServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @Test
    @Order(1)
    public void createPurchaseTest() throws Exception {

        ClientPostDTO clientPostDTO = new ClientPostDTO("Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "34331222", UserState.ACTIVO);

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int clientCode = clientService.createClient(clientPostDTO);

        List<String> images = new ArrayList<>();
        images.add("http://www.google.com/images/imagenasus.png");
        images.add("http://www.google.com/images/imagenasus_original.png");

        ProductPostDTO productPostDTO = new ProductPostDTO(
                "Sandwichera 4",
                "Sanduchera de 4 slots para ricos sandwiches",
                6,
                40000,
                clientCode,
                images,
                List.of(Category.TECNOLOGIA, Category.HOGAR));

        //Se llama el servicio para crear el producto
        int code = productService.createProduct(productPostDTO);

        ProductPostDTO productPostDTO2 = new ProductPostDTO(
                "Sandwichera 8",
                "Sanduchera de 8 slots para ricos sandwiches",
                2,
                60000,
                clientCode,
                images,
                List.of(Category.TECNOLOGIA, Category.HOGAR));

        //Se llama el servicio para crear el producto
        int code2 = productService.createProduct(productPostDTO2);

        List<Integer> productCode = new ArrayList<>();
        productCode.add(code);
        productCode.add(code2);

        List<Integer> productAmount = new ArrayList<>();
        productAmount.add(1);
        productAmount.add(1);

        List<Float> productPrice = new ArrayList<>();
        productPrice.add(Float.parseFloat("60000"));
        productPrice.add(Float.parseFloat("40000"));

        float totalPrice = productPrice.get(0) + productPrice.get(1);

        PurchasePostDTO purchasePostDTO = new PurchasePostDTO(
                clientCode,
                totalPrice,
                "Tarjeta",
                productCode,
                productAmount,
                productPrice
        );

        int code3 = purchaseService.createPurchase(purchasePostDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        Assertions.assertEquals(1, code);
    }

}
