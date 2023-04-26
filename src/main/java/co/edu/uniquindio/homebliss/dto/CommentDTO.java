package co.edu.uniquindio.homebliss.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CommentDTO {

    private String message;

    private int userCode;

    private int productCode;

}
