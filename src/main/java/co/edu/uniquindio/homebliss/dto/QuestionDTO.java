package co.edu.uniquindio.homebliss.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class QuestionDTO {

    @NotNull
    private String answer;

    @NotNull
    private Integer clientId;

    @NotNull
    private Integer productId;

}
