package co.edu.uniquindio.homebliss.test;

import co.edu.uniquindio.homebliss.dto.ClientPostDTO;
import co.edu.uniquindio.homebliss.dto.ProductPostDTO;
import co.edu.uniquindio.homebliss.dto.QuestionDTO;
import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.model.UserState;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import co.edu.uniquindio.homebliss.services.interfaces.QuestionService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private QuestionService questionService;

    @Test
    @Order(1)
    public void createQualificationTest() throws Exception{
        int code = createSimpleQualification();
        Assertions.assertEquals(code, 1);
    }

    @Test
    @Order(2)
    public void deleteQualificationTest() throws Exception{
        int code = createSimpleQualification();
        Assertions.assertTrue(questionService.deleteQuestion(code));
    }

    @Test
    @Order(3)
    public void deleteQualificationNotExistsTest() {
        Assertions.assertTrue(questionService.deleteQuestion(1000));
    }

    private int createSimpleQualification() throws Exception {
        ClientPostDTO clientPostDTO = new ClientPostDTO("Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "34331222", UserState.ACTIVO);

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int clientCode = clientService.createClient(clientPostDTO);

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
                clientCode,
                images,
                List.of(Category.TECNOLOGIA)
        );

        //Se llama el servicio para crear el producto
        int productCode = productService.createProduct(productPostDTO);

        //Se crea el usuario con el servicio de crearUsuario
        QuestionDTO dto = new QuestionDTO(
                "Pepito 1",
                clientCode,
                productCode,
                "Pepito 1");

        return questionService.createQuestion(dto);
    }

}

