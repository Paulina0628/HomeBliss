package co.edu.uniquindio.homebliss.services.interfaces;


import co.edu.uniquindio.homebliss.dto.ClientDTO;
import co.edu.uniquindio.homebliss.model.Client;

public interface ClientService {

    int createClient(ClientDTO clientDTO) throws Exception;

    int updateClient(int clientCode, ClientDTO clientDTO) throws Exception;

    int deleteClient(int clientCode) throws Exception;

    ClientDTO getClient(int clientCode) throws Exception;

}
