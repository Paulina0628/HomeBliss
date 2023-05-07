package co.edu.uniquindio.homebliss.controllers;


import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.dto.ProductGetDTO;
import co.edu.uniquindio.homebliss.dto.ProductPostDTO;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.services.interfaces.CloudinaryService;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public List<ProductGetDTO> list() {
        return productService.getProducts();
    }

    @PostMapping("/register")
    public ResponseEntity<MessageDTO> register(@Valid @RequestBody ProductPostDTO product) throws Exception {

        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Producto creado correctamente"));
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
            images.add(nombreArchivo);
            //Se sube el archivo a la nube
            File file = cloudinaryService.convert(archive);
            cloudinaryService.uploadImage(file, nombreArchivo);
            product.setImages(images);
            //Se actualiza la información del producto
            productService.update(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                    false, "Imagen cargada correctamente"));
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                    true, "Se presentó un  error al momento de carga la imagen"));
        }
    }
}