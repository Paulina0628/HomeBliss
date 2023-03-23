package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.PurchaseDTO;
import co.edu.uniquindio.homebliss.dto.PurchaseDetailDTO;
import co.edu.uniquindio.homebliss.dto.PurchaseGetDTO;

import java.util.List;

public interface PurchaseService {

    int createpurchase(PurchaseDTO purchaseDTO);

    PurchaseGetDTO getPurchase(int purchaseCode);

    List<PurchaseGetDTO> getPurchases(int userCode);

}
