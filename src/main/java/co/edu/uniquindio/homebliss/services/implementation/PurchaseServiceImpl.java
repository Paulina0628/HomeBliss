package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.dto.PurchasePostDTO;
import co.edu.uniquindio.homebliss.dto.PurchaseGetDTO;
import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.ProductState;
import co.edu.uniquindio.homebliss.model.Purchase;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.repositories.PurchaseRepository;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientService clientService;

    @Override
    public int createpurchase(PurchasePostDTO purchasePostDTO) throws Exception {
        Purchase purchase = new Purchase();

        Client client = clientService.getClient(purchasePostDTO.getClientCode());

        purchase.setClient(client);
        purchase.setCreated_date(LocalDateTime.now());
        purchase.setTotal_price(purchasePostDTO.getTotalPrice());
        purchase.setPayment_method(purchasePostDTO.getPaymentMethod());

        return purchaseRepository.save( purchase ).getId();
    }

    @Override
    public PurchaseGetDTO getPurchase(int clientCode) {

        List<Purchase> list = purchaseRepository.findAllByUser(clientCode);
        List<PurchaseGetDTO> answer = new ArrayList<>();


        for(Purchase p : list){
            answer.add( toPurchaseDTO(p));
        }

        return answer;
    }

    @Override
    public List<PurchaseGetDTO> getPurchases(int userCode) {
        return null;
    }

    public PurchaseGetDTO toPurchaseDTO(Purchase purchase){

        PurchaseGetDTO purchaseGetDTO = new PurchaseGetDTO(purchase.getId(),
                purchase.getClient().getId(),
                purchase.getCreated_date(),
                purchase.getTotal_price(),
                purchase.getPayment_method());

        return purchaseGetDTO;
    }
}
