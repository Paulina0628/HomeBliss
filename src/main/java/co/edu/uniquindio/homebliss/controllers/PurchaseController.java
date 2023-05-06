package co.edu.uniquindio.homebliss.controllers;


import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.dto.PurchaseGetDTO;
import co.edu.uniquindio.homebliss.dto.PurchasePostDTO;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import co.edu.uniquindio.homebliss.services.interfaces.PurchaseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public List<PurchaseGetDTO> listAll() {
        return purchaseService.getListAllPurchases();
    }

    @GetMapping("/{userCode}")
    public List<PurchaseGetDTO> list(@PathVariable int userCode) {
        return purchaseService.getPurchases(userCode);
    }

    @PostMapping
    public ResponseEntity<MessageDTO> register(@Valid @RequestBody PurchasePostDTO purchase) throws Exception {

        purchaseService.createPurchase(purchase);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Compra creada correctamente"));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<MessageDTO> delete (@PathVariable int code) throws Exception {
        purchaseService.deletePurchase(code);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Compra eliminada correctamente"));
    }

    @PutMapping
    public ResponseEntity<MessageDTO> update (@Valid @RequestBody PurchasePostDTO purchase) throws Exception {
        purchaseService.createPurchase(purchase);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Compra actualizada correctamente"));
    }

}
