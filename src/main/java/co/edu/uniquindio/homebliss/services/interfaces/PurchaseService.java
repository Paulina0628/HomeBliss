package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.PurchasePostDTO;
import co.edu.uniquindio.homebliss.dto.PurchaseGetDTO;

import java.util.List;

public interface PurchaseService {

    int createpurchase(PurchasePostDTO purchasePostDTO) throws Exception;

    PurchaseGetDTO getPurchase(int purchaseCode);

    List<PurchaseGetDTO> getPurchases(int userCode);

}
