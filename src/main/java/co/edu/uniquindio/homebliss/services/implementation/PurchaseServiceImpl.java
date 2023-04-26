package co.edu.uniquindio.homebliss.services.implementation;


import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.dto.PurchasePostDTO;
import co.edu.uniquindio.homebliss.dto.PurchaseGetDTO;
import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.Purchase;
import co.edu.uniquindio.homebliss.model.PurchaseDetail;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.repositories.PurchaseRepository;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import co.edu.uniquindio.homebliss.services.interfaces.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
    private ProductService productService;

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

        int code = purchaseRepository.save( purchase ).getId();

        Purchase purchase2 = getPurchase(code);
        List<PurchaseDetail> purchaseDetails = new ArrayList<>();

        for (int i = 0; i < purchasePostDTO.getProductCode().size(); i++){
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setAmount(purchasePostDTO.getProductAmount().get(i));
            purchaseDetail.setProduct_price(purchasePostDTO.getProductPrice().get(i));
            purchaseDetail.setPurchase(purchase2);
            purchaseDetail.setProduct(productService.getProduct(purchasePostDTO.getProductCode().get(i)));

            purchaseDetails.add(purchaseDetail);
        }

        purchase2.setPurchaseDetails(purchaseDetails);

        return purchaseRepository.save( purchase ).getId();
    }

    @Override
    public PurchaseGetDTO getPurchaseDTO(int purchaseCode) throws Exception {
        return toPurchaseDTO(getPurchase(purchaseCode));
    }

    @Override
    public Purchase getPurchase(int purchaseCode) throws Exception {

        Optional<Purchase> purchase = purchaseRepository.findById(purchaseCode);

        if(purchase.isEmpty() ){
            throw new Exception("El código " + purchaseCode + " no está asociado a ningún producto");
        }
        return purchase.get();
    }

    @Override
    public List<PurchaseGetDTO> getPurchases(int userCode) {
        List<Purchase> list = purchaseRepository.findAllByUser(userCode);
        List<PurchaseGetDTO> answer = new ArrayList<>();

        for(Purchase p : list){
            answer.add(toPurchaseDTO(p));
        }

        return answer;
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
