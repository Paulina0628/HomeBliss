package co.edu.uniquindio.homebliss.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {
    Map uploadImage(File file, String folder) throws Exception;

    Map deleteImage(String imageId) throws Exception;

    File convert(MultipartFile image) throws IOException;
}
