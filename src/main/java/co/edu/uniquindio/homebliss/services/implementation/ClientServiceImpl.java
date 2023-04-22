package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.ClientPostDTO;
import co.edu.uniquindio.homebliss.dto.ClientGetDTO;
import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.UserStatus;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.services.exceptions.AttributeException;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int createClient(ClientPostDTO clientPostDTO) throws Exception {

        if(!isAvailable(clientPostDTO.getEmail())) {
            throw new AttributeException("El correo " + clientPostDTO.getEmail() + " ya está en uso");
        }

        Client client = toClient(clientPostDTO);

        return clientRepository.save(client).getId();
    }

    @Override
    public ClientGetDTO updateClient(int clientCode, ClientPostDTO clientPostDTO) throws Exception {

        if(!validateClient(clientCode)){
            throw new Exception("El código " + clientCode + " no está asociado a ningún usuario");
        }

        Client client = toClient(clientPostDTO);
        client.setId(clientCode);

        return toClientDTO(clientRepository.save(client));
    }

    @Override
    public void deleteClient(int clientCode) throws Exception {

        Client client = getClient(clientCode);
        client.setStatus(UserStatus.INACTIVO);

        clientRepository.save(client);
    }

    @Override
    public ClientGetDTO getClientDTO(int clientCode) throws Exception {
        return toClientDTO(getClient(clientCode));
    }

    @Transactional(readOnly = true)
    public Client getClient(int clientCode) throws Exception {
        Optional<Client> client = clientRepository.findById(clientCode);

        if(client.isEmpty() ){
            throw new Exception("El código " + clientCode + " no está asociado a ningún usuario");
        }
        return client.get();
    }

    @Override
    public List<ClientGetDTO> getClients() {
        return toClientDTOList(clientRepository.findAll());
    }

    public boolean validateClient(int clientCode) throws Exception{
        Optional<Client> client = clientRepository.findById(clientCode);
        return client.isEmpty();
    }

    public ClientGetDTO toClientDTO(Client client){

        ClientGetDTO clientGetDTO = new ClientGetDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getAddress(),
                client.getPhone());

        return clientGetDTO;
    }

    public Client toClient(ClientPostDTO clientPostDTO){

        Client client = new Client();

        client.setName(clientPostDTO.getName());
        client.setLastname(clientPostDTO.getLastname());
        client.setEmail(clientPostDTO.getEmail());
        client.setAddress(clientPostDTO.getAddress());
        client.setPhone(clientPostDTO.getPhone());
        client.setPassword(passwordEncoder.encode(clientPostDTO.getPassword()));

        return client;
    }

    public List<ClientGetDTO> toClientDTOList (List<Client> clients){
        List<ClientGetDTO> answer = new ArrayList<>();

        for(Client client : clients){
            answer.add(toClientDTO(client));
        }

        return answer;
    }

    public boolean isAvailable(String email){
        Optional<Client> client = clientRepository.findByEmail(email);
        return client.isEmpty();
    }
}
