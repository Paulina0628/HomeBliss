package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.QuestionDTO;

public interface QuestionService {

    int createQuestion(QuestionDTO questionDTO);
    boolean deleteQuestion(int questionId);


}
