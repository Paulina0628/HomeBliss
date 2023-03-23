package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.ClientDTO;
import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public int createClient(ClientDTO clientDTO) throws Exception{
        Client client = clientRepository.searchClient(clientDTO.getEmail());

        if(client != null){
            throw new Exception("El correo " + clientDTO.getEmail() + " ya existe");
        }

        return 0;
    }

    @Override
    public int updateClient(int clientCode, ClientDTO clientDTO) throws Exception {
        validateClient(clientCode);

        Optional<Client> finded = clientRepository.findById(clientCode);
        Client client = finded.get();

        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setPassword(clientDTO.getPassword());
        client.setState(clientDTO.getState());

        return 0;
    }

    @Override
    public int deleteClient(int clientCode) throws Exception {
        validateClient(clientCode);
        clientRepository.deleteById(clientCode);
        return clientCode;
    }

    @Override
    public ClientDTO getClient(int clientCode) throws Exception {
        validateClient(clientCode);

        Client client = clientRepository.findById(clientCode).get();

        ClientDTO clientDTO = toDTO(client);

        return clientDTO;
    }

    private void validateClient(int clientCode) throws Exception{

        Boolean client = clientRepository.existsById(clientCode);

        if(!client){
            throw new Exception("El cliente no existe");
        }

    }

    private ClientDTO toDTO(Client client){

        ClientDTO clientDTO = new ClientDTO(
                client.getName(),
                client.getLastname(),
                client.getPhone(),
                client.getAddress(),
                client.getEmail(),
                client.getPassword(),
                client.getState()
        );

        return clientDTO;
    }
}
