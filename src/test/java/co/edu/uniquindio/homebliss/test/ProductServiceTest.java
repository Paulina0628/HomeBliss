package co.edu.uniquindio.homebliss.test;

import co.edu.uniquindio.homebliss.dto.ClientDTO;
import co.edu.uniquindio.homebliss.dto.ProductDTO;
import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Test
    @Sql("classpath:dataset.sql" )
    public void createProductTest() throws Exception {

        ClientDTO clientDTO = new ClientDTO("Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "34332",
                "Activo");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int sellerCode = clientService.createClient(clientDTO);

        //Se crea la colección de imágenes para el producto.
        List<String> images = new ArrayList<>();
        images.add("http://www.google.com/images/imagenasus.png");
        images.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductDTO productDTO = new ProductDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                sellerCode,
                images,
                List.of(Category.TECNOLOGIA)
        );

        //Se llama el servicio para crear el producto
        int productCode = productService.createProduct(productDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        Assertions.assertNotEquals(1, productCode);
    }

}
