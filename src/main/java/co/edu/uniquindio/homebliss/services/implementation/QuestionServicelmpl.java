package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.QualificationDTO;
import co.edu.uniquindio.homebliss.dto.QuestionDTO;
import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.Qualification;
import co.edu.uniquindio.homebliss.model.Question;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.repositories.QualificationRepository;
import co.edu.uniquindio.homebliss.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionServicelmpl {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public void createQuestion (QuestionDTO questionDTO){

        Question question = toQuestion(questionDTO);

        Client client = question.getClient();
        Product product = question.getProduct();

        client.getQuestions().add(question);
        product.getQuestions().add(question);

        questionRepository.save(question);
        clientRepository.save(client).getId();
        productRepository.save(product).getId();
    }

    public void deleteQuestion(Integer questionId){

        Question question = questionRepository.getReferenceById(questionId);
        questionRepository.delete(question);
    }

    public Question toQuestion(QuestionDTO questionDTO){

        Client client = clientRepository.getReferenceById(questionDTO.getClientId());
        Product product = productRepository.getReferenceById(questionDTO.getProductId());

        Question question = new Question();

        question.setAnswer(questionDTO.getAnswer());
        question.setClient(client);
        question.setProduct(product);

        return question;

    }
}
