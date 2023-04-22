package co.edu.uniquindio.homebliss.services.interfaces;


import co.edu.uniquindio.homebliss.dto.ClientPostDTO;
import co.edu.uniquindio.homebliss.dto.ClientGetDTO;
import co.edu.uniquindio.homebliss.model.Client;

import java.util.List;

public interface ClientService {

    int createClient(ClientPostDTO clientPostDTO) throws Exception;

    ClientGetDTO updateClient(int clientCode, ClientPostDTO clientPostDTO) throws Exception;

    void deleteClient(int clientCode) throws Exception;

    ClientGetDTO getClientDTO(int clientCode) throws Exception;

    Client getClient(int clientCode) throws Exception;

    List<ClientGetDTO> getClients();

}
