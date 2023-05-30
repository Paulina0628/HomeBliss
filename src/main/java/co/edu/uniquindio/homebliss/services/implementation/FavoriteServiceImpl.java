package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.services.interfaces.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean changeState(int clientId, int productCode) {

        Client client = this.clientRepository.getReferenceById(clientId);
        Product product = this.productRepository.getReferenceById(productCode);

        boolean nextState = false;
        if (client.getFavorites().contains(product)) {
            product.getClientFavorite().removeIf(c -> c.getId() == clientId);
            client.getFavorites().removeIf(p -> p.getId() == productCode);
        } else {
            product.getClientFavorite().add(client);
            client.getFavorites().add(product);
            nextState = true;
        }

        this.clientRepository.save(client);
        this.productRepository.save(product);
        return nextState;
    }

}
