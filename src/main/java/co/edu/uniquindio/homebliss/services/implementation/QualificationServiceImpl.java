package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.QualificationDTO;
import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.Qualification;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.repositories.QualificationRepository;
import co.edu.uniquindio.homebliss.services.interfaces.QualificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QualificationServiceImpl implements QualificationService {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    public int createQualification(QualificationDTO qualificationDTO) throws Exception{

        Qualification qualification = toQualification(qualificationDTO);

        Client client = qualification.getClient();
        Product product = qualification.getProduct();

        if (client.getQualifications() == null) client.setQualifications(new ArrayList<>());
        client.getQualifications().add(qualification);

        if (product.getQualifications() == null) product.setQualifications(new ArrayList<>());
        product.getQualifications().add(qualification);

        int id = qualificationRepository.save(qualification).getId();
        clientRepository.save(client);
        productRepository.save(product);

        return id;
    }

    @Override
    public boolean deleteQualification(int qualificationId) {
        Qualification qualification = qualificationRepository.getById(qualificationId);
        if (qualification == null) {
            return false;
        }
        qualificationRepository.delete(qualification);
        return true;
    }



    public Qualification toQualification(QualificationDTO qualificationDTO){

        Client client = clientRepository.getReferenceById(qualificationDTO.getUserCode());
        Product product = productRepository.getReferenceById(qualificationDTO.getProductCode());

        Qualification qualification = new Qualification();

        qualification.setMessage(qualificationDTO.getMessage());
        qualification.setTitle(qualificationDTO.getTitle());
        qualification.setClient(client);
        qualification.setProduct(product);

        return qualification;

    }

}
