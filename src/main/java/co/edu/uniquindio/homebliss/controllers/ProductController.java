package co.edu.uniquindio.homebliss.controllers;


import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.dto.ProductPostDTO;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductGetDTO> list(){
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<MessageDTO> register (@Valid @RequestBody ProductPostDTO product) throws Exception {

        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Producto creado correctamente"));
    }
    @DeleteMapping("/{code}")
    public ResponseEntity<MessageDTO> delete (@PathVariable int code) throws Exception {

        productService.deleteProduct(code);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Producto eliminado correctamente"));
    }

    @PutMapping("/{code}")
    public ResponseEntity<MessageDTO> update (@Valid @RequestBody ProductPostDTO product, @PathVariable int code) throws Exception {
        productService.updateProduct(code, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Producto actualizado correctamente"));
    }
}