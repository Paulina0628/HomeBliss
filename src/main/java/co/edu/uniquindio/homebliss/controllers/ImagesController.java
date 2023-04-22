package co.edu.uniquindio.homebliss.controllers;

import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.services.interfaces.CloudinaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@AllArgsConstructor
public class ImagesController {

    private final CloudinaryService cloudinaryService;
    @PostMapping("/upload")
    public ResponseEntity<MessageDTO> uploadImage(@RequestParam("file") MultipartFile file)
            throws Exception{
        File image = cloudinaryService.convert(file);
        Map response = cloudinaryService.uploadImage(image, "homebliss");
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,
                response ) );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> deleteImage(@PathVariable String id) throws Exception{
        Map response = cloudinaryService.deleteImage(id);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,
                response ) );
    }
}
