package co.edu.uniquindio.homebliss.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionDTO extends CommentDTO {

    @NotNull
    private String answer;

    public QuestionDTO(String message, int userCode, int productCode, String answer) {
        super(message, userCode, productCode);
        this.answer = answer;
    }

}
