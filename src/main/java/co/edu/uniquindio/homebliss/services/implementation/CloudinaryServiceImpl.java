package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.services.interfaces.CloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryServiceImpl implements CloudinaryService {
    private Cloudinary cloudinary;
    public CloudinaryServiceImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "SU_CLOUD_NAME");
        config.put("api_key", "SU_API_KEY");
        config.put("api_secret", "SU_API_SECRET");
        cloudinary = new Cloudinary(config);
    }
    @Override
    public Map uploadImage(File file, String folder) throws Exception{
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder",
                String.format("uniquindio/proyecto/%s", folder)));
    }
    @Override
    public Map deleteImage(String idImagen) throws Exception{
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }
    @Override
    public File convert(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());

        fos.close();
        return file;
    }
}
