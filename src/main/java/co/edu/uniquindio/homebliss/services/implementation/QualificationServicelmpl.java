package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.QualificationDTO;
import co.edu.uniquindio.homebliss.model.*;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.repositories.QualificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class QualificationServicelmpl{


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    public void createQualification(QualificationDTO qualificationDTO) throws Exception{

        Qualification qualification = toQualification(qualificationDTO);

        Client client = qualification.getClient();
        Product product = qualification.getProduct();

        client.getQualifications().add(qualification);
        product.getQualifications().add(qualification);

        qualificationRepository.save(qualification);
        clientRepository.save(client).getId();
        productRepository.save(product).getId();
    }

    public void DeleteQualification (Integer qualificationId) throws Exception{

        Qualification qualification = qualificationRepository.getReferenceById(qualificationId);
        qualificationRepository.delete(qualification);
    }


    public Qualification toQualification(QualificationDTO qualificationDTO){

        Client client = clientRepository.getReferenceById(qualificationDTO.getClientId());
        Product product = productRepository.getReferenceById(qualificationDTO.getProductId());

        Qualification qualification = new Qualification();

        qualification.setTitle(qualificationDTO.getTittle());
        qualification.setClient(client);
        qualification.setProduct(product);

        return qualification;

    }

}
