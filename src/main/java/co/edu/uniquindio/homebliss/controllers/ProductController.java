package co.edu.uniquindio.homebliss.controllers;


import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.dto.ProductPostDTO;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.User;
import co.edu.uniquindio.homebliss.services.interfaces.CloudinaryService;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import co.edu.uniquindio.homebliss.services.interfaces.SessionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public List<ProductGetDTO> list() {
        return productService.getProducts();
    }

    @GetMapping("/by-client")
    public ResponseEntity<MessageDTO> getByClient() throws Exception {
        User user = sessionService.getCurrentUser();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO(HttpStatus.OK, false, Collections.emptyList()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO(HttpStatus.OK, false,
                productService.getFavoritesProducts(user.getId())
        ));
    }

    @GetMapping("/by-client/{productCode}")
    public ResponseEntity<MessageDTO> getByClient(@PathVariable int productCode) throws Exception {
        User user = sessionService.getCurrentUser();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO(HttpStatus.OK, false, productService.getProductDTO(productCode)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO(HttpStatus.OK, false, productService.getProductByClient(user.getId(), productCode)));
    }

    @GetMapping("/{code}")
    public ResponseEntity<MessageDTO> get(@PathVariable int code) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO(HttpStatus.OK, false,
                productService.getProductDTO(code)));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<MessageDTO> getProductsByCategory(@PathVariable String category, @RequestParam String q) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO(HttpStatus.OK, false,
                productService.getProductsByCategory(category, q)));
    }

    @GetMapping("/client/{code}")
    public ResponseEntity<MessageDTO> getProductsByClient(@PathVariable int code) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO(HttpStatus.OK, false,
                productService.getProductsByClient(code)));
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDTO> register(@Valid @RequestBody ProductPostDTO product) throws Exception {
        int id = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED, false, id));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<MessageDTO> delete(@PathVariable int code) throws Exception {
        productService.deleteProduct(code);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Producto eliminado correctamente"));
    }

    @PutMapping("/{code}")
    public ResponseEntity<MessageDTO> update(@Valid @RequestBody ProductPostDTO product, @PathVariable int code) throws Exception {
        productService.updateProduct(code, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Producto actualizado correctamente"));
    }

    @PostMapping("/upload")
    public ResponseEntity<MessageDTO> upload(@RequestParam("archive") MultipartFile archive, @RequestParam("id") int productId) throws Exception {
        List<String> images = new ArrayList<>();
        Product product = productService.getProduct(productId);
        String nombreArchivo = "";
        if (product != null && !archive.isEmpty()) {
            //Se crea un nombre único para el archivo
            nombreArchivo = UUID.randomUUID() + "_" + archive.getOriginalFilename().replace(" ", "");
            //Se agregan las imágenes a la lista

            //Se sube el archivo a la nube
            File file = cloudinaryService.convert(archive);
            Map result = cloudinaryService.uploadImage(file, nombreArchivo);
            String url = String.valueOf(result.get("secure_url"));
            images.add(url);
            product.setImages(images);
            //Se actualiza la información del producto
            productService.update(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                    false, url));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(HttpStatus.BAD_REQUEST,
                    true, "Se presentó un  error al momento de carga la imagen"));
        }
    }
}