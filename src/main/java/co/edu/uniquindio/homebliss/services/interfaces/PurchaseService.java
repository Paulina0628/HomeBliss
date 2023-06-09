package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.PurchasePostDTO;
import co.edu.uniquindio.homebliss.dto.PurchaseGetDTO;
import co.edu.uniquindio.homebliss.model.Purchase;
import co.edu.uniquindio.homebliss.model.PurchaseDetail;

import java.util.List;

public interface PurchaseService {

    int createPurchase(PurchasePostDTO purchasePostDTO) throws Exception;

    Purchase getPurchase(int purchaseCode) throws Exception;

    PurchaseGetDTO getPurchaseDTO(int purchaseCode) throws Exception;

    List<PurchaseGetDTO> getPurchases(int userCode);

    List<PurchaseGetDTO> getListAllPurchases();

    void deletePurchase (int code) throws Exception;

    void sendPurchaseEmail(List<PurchaseDetail> purchaseDetailList, Purchase purchase);

}
