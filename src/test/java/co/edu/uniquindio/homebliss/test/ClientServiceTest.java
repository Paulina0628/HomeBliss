package co.edu.uniquindio.homebliss.test;

import co.edu.uniquindio.homebliss.dto.ClientPostDTO;
import co.edu.uniquindio.homebliss.dto.ClientGetDTO;
import co.edu.uniquindio.homebliss.model.UserState;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void createClientTest() throws Exception{

        try{
            //Se crea el usuario con el servicio de crearUsuario
            ClientPostDTO clientPostDTO = new ClientPostDTO(
                    "Pepito 1",
                    "Alvarez",
                    "12312312312",
                    "Calle 123",
                    "pepe1@email.com",
                    "452335",
                    UserState.ACTIVO);

            int code = clientService.createClient(clientPostDTO);

            //Se espera que si se registra correctamente entonces el servicio no debe retornar 0
            Assertions.assertNotEquals(0, code);

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }


    @Test
    public void deleteClientTest() throws Exception{

        //Para eliminar el usuario primero se debe crear
        ClientPostDTO clientPostDTO = new ClientPostDTO(
                "Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "452335",
                UserState.ACTIVO);

        int code = clientService.createClient(clientPostDTO);

        //Una vez creado, lo borramos
        clientService.deleteClient(code);

        //Si intentamos buscar un usuario con el codigo del usuario borrado se comprueba que está en estado INACTIVO
        Assertions.assertEquals(UserState.INACTIVO, clientService.getClient(code).getState());
    }

    @Test
    public void updateClientTest() throws Exception{

        //Para actualizar el usuario primero se debe crear
        ClientPostDTO clientPostDTO = new ClientPostDTO(
                "Pepito 1",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "45237735",
                UserState.ACTIVO);

        int newCode = clientService.createClient(clientPostDTO);

        //El servicio de actualizar nos retorna el usuario
        ClientGetDTO updatedClient = clientService.updateClient(newCode, new ClientPostDTO("Pepito",
                "Perez",
                "123123122",
                "Calle 12223",
                "pepeperez@email.com",
                "45772335",
                UserState.ACTIVO));

        //Se comprueba que ahora el teléfono del usuario no es el que se usó cuando se creó inicialmente
        Assertions.assertNotEquals("12312312312", updatedClient.getPhone());

    }

    @Test
    public void getClientTest() throws Exception{

        //Para obtener el usuario primero se debe crear
        ClientPostDTO clientPostDTO = new ClientPostDTO(
                "Pepito",
                "Alvarez",
                "12312312312",
                "Calle 123",
                "pepe1@email.com",
                "452335",
                UserState.ACTIVO);

        int newCode = clientService.createClient(clientPostDTO);

        //Se llama el servicio para obtener el usuario completo dado su código
        ClientGetDTO clientGetDTO = clientService.getClientDTO(newCode);

        //Comprobamos que la dirección que está en la base de datos coincide con la que esperamos
        Assertions.assertEquals("Calle 123", clientGetDTO.getAddress());

    }

}
