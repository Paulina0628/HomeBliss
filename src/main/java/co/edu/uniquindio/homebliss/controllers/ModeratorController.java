package co.edu.uniquindio.homebliss.controllers;

import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.ProductState;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moderator")
@PreAuthorize("hasAuthority('MODERATOR')")
public class ModeratorController {

    @Autowired
    private ProductService productService;

    @PutMapping("/changeState/{productCode}")
    public ResponseEntity<MessageDTO> changeStateProduct(@Valid @RequestParam("state") String state, @PathVariable int productCode) throws Exception {

        switch (state) {
            case "ACTIVO" -> productService.updateState(productCode, ProductState.ACTIVO);
            case "INACTIVO" -> productService.updateState(productCode, ProductState.INACTIVO);
            case "ELIMINADO" -> productService.updateState(productCode, ProductState.ELIMINADO);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Producto actualizado correctamente"));
    }

}
