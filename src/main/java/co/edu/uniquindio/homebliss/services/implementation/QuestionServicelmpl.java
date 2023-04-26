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
import co.edu.uniquindio.homebliss.services.interfaces.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class QuestionServicelmpl implements QuestionService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public int createQuestion(QuestionDTO questionDTO){

        Question question = toQuestion(questionDTO);

        Client client = question.getClient();
        Product product = question.getProduct();

        if (client.getQuestions() == null) client.setQuestions(new ArrayList<>());
        client.getQuestions().add(question);

        if (product.getQuestions() == null) product.setQuestions(new ArrayList<>());
        product.getQuestions().add(question);

        int id = questionRepository.save(question).getId();
        clientRepository.save(client);
        productRepository.save(product);

        return id;
    }

    public boolean deleteQuestion(int questionId){
        Question question = questionRepository.getReferenceById(questionId);
        if (question == null) {
            return false;
        }
        questionRepository.delete(question);
        return true;
    }

    public Question toQuestion(QuestionDTO questionDTO){

        Client client = clientRepository.getReferenceById(questionDTO.getUserCode());
        Product product = productRepository.getReferenceById(questionDTO.getProductCode());

        Question question = new Question();
        question.setMessage(questionDTO.getMessage());
        question.setAnswer(questionDTO.getAnswer());
        question.setClient(client);
        question.setProduct(product);

        return question;

    }
}
