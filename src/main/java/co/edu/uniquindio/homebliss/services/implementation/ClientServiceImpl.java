package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.ClientDTO;
import co.edu.uniquindio.homebliss.dto.ClientGetDTO;
import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public int createClient(ClientDTO clientDTO) throws Exception {

        Client searched = clientRepository.searchClient(clientDTO.getEmail());

        if(searched != null){
            throw new Exception("El correo " + clientDTO.getEmail() + " ya está en uso");
        }

        Client client = toClient(clientDTO);

        return clientRepository.save(client).getId();
    }

    @Override
    public ClientGetDTO updateClient(int clientCode, ClientDTO clientDTO) throws Exception {

        validateClient(clientCode);

        Client client = toClient(clientDTO);
        client.setId(clientCode);

        return toClientDTO(clientRepository.save(client));
    }

    @Override
    public int deleteClient(int clientCode) throws Exception {
        validateClient(clientCode);
        clientRepository.deleteById(clientCode);
        return clientCode;
    }

    @Override
    public ClientGetDTO getClientDTO(int clientCode) throws Exception {
        return toClientDTO(getClient(clientCode));
    }

    @Override
    public Client getClient(int clientCode) throws Exception {
        Optional<Client> client = clientRepository.findById(clientCode);

        if(client.isEmpty() ){
            throw new Exception("El código "+ clientCode +" no está asociado a ningún usuario");
        }
        return client.get();
    }

    private void validateClient(int clientCode) throws Exception{

        Boolean client = clientRepository.existsById(clientCode);

        if(!client){
            throw new Exception("El código "+ clientCode +" no está asociado a ningún usuario");
        }

    }

    private ClientGetDTO toClientDTO(Client client){

        ClientGetDTO clientGetDTO = new ClientGetDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getAddress(),
                client.getPhone());

        return clientGetDTO;
    }

    private Client toClient(ClientDTO clientDTO){

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setEmail(clientDTO.getEmail());
        client.setAddress(clientDTO.getAddress());
        client.setPhone(clientDTO.getPhone());
        client.setPassword(clientDTO.getPassword());
        client.setState(clientDTO.getState());

        return client;
    }
}
