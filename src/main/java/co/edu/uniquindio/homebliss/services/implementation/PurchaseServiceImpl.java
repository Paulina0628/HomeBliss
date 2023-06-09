package co.edu.uniquindio.homebliss.services.implementation;


import co.edu.uniquindio.homebliss.dto.EmailDTO;
import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.dto.PurchasePostDTO;
import co.edu.uniquindio.homebliss.dto.PurchaseGetDTO;
import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.ProductState;
import co.edu.uniquindio.homebliss.model.Purchase;
import co.edu.uniquindio.homebliss.model.PurchaseDetail;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.repositories.PurchaseRepository;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.EmailService;
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

    @Autowired
    private EmailService emailService;

    @Override
    public int createPurchase(PurchasePostDTO purchasePostDTO) throws Exception {
        Purchase purchase = new Purchase();

        Client client = clientService.getClient(purchasePostDTO.getClientCode());

        purchase.setClient(client);
        purchase.setCreated_date(LocalDateTime.now());
        purchase.setTotal_price(purchasePostDTO.getTotalPrice());
        purchase.setPayment_method(purchasePostDTO.getPaymentMethod());

        int code = purchaseRepository.save(purchase).getId();

        Purchase purchase2 = getPurchase(code);
        List<PurchaseDetail> purchaseDetails = new ArrayList<>();

        for (int i = 0; i < purchasePostDTO.getProductCode().size(); i++) {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setAmount(purchasePostDTO.getProductAmount().get(i));
            purchaseDetail.setProduct_price(purchasePostDTO.getProductPrice().get(i));
            purchaseDetail.setPurchase(purchase2);
            purchaseDetail.setProduct(productService.getProduct(purchasePostDTO.getProductCode().get(i)));

            purchaseDetails.add(purchaseDetail);
        }
        //¿Qué pasa con purchase2? ¿Para qué se usa? o ¿por qué no se guarda?
        purchase2.setPurchaseDetails(purchaseDetails);
        //Envío de correo electrónico
        sendPurchaseEmail(purchaseDetails, purchase);

        return purchaseRepository.save(purchase).getId();
    }

    @Override
    public PurchaseGetDTO getPurchaseDTO(int purchaseCode) throws Exception {
        return toPurchaseDTO(getPurchase(purchaseCode));
    }

    @Override
    public Purchase getPurchase(int purchaseCode) throws Exception {

        Optional<Purchase> purchase = purchaseRepository.findById(purchaseCode);

        if (purchase.isEmpty()) {
            throw new Exception("El código " + purchaseCode + " no está asociado a ningún producto");
        }
        return purchase.get();
    }

    @Override
    public List<PurchaseGetDTO> getPurchases(int userCode) {
        List<Purchase> list = purchaseRepository.findAllByUser(userCode);
        List<PurchaseGetDTO> answer = new ArrayList<>();

        for (Purchase p : list) {
            answer.add(toPurchaseDTO(p));
        }

        return answer;
    }

    @Override
    public List<PurchaseGetDTO> getListAllPurchases() {
        List<Purchase> list = purchaseRepository.findAll();
        List<PurchaseGetDTO> answer = new ArrayList<>();
        for (Purchase p : list) {
            answer.add(toPurchaseDTO(p));
        }
        return answer;
    }

    @Override
    public void deletePurchase(int code) throws Exception {
        Purchase purchase = purchaseRepository.findById(code).orElse(null);
        if (purchase == null) {
            throw new Exception("La compra no existe");
        } else {
            purchaseRepository.delete(purchase);
        }
    }

    @Override
    public void sendPurchaseEmail(List<PurchaseDetail> purchaseDetailList, Purchase purchase) {
        String message = "Compra realizada con éxito\n " +
                "Detalles de la compra: \n" +
                "Fecha: " + purchase.getCreated_date() + "\n" +
                "Cliente: " + purchase.getClient().getName() + " " + purchase.getClient().getLastname() + "\n" +
                "Precio total: " + purchase.getTotal_price() + "\n" +
                "Detalles de la compra: \n";
        try {
            for (PurchaseDetail p : purchaseDetailList) {
                message += "Producto " + p.getProduct().getName() + "-----> $" + p.getProduct().getPrice();
            }
            // Se envía notificación al cliente
            emailService.sendEmail(new EmailDTO("Resumen de la compra ", message, purchase.getClient().getEmail()));
            // Se envía notificación al vendedor, para eso se toma del primer producto el correo electrónico del comprador
            emailService.sendEmail(new EmailDTO("Resumen de la compra ", message, purchase.getPurchaseDetails().get(0)
                    .getProduct().getSeller().getEmail()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PurchaseGetDTO toPurchaseDTO(Purchase purchase) {

        PurchaseGetDTO purchaseGetDTO = new PurchaseGetDTO(purchase.getId(),
                purchase.getClient().getId(),
                purchase.getCreated_date(),
                purchase.getTotal_price(),
                purchase.getPayment_method());

        return purchaseGetDTO;
    }
}
