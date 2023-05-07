package co.edu.uniquindio.homebliss.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CommentDTO {

    private  int id;

    private String message;

    private int userCode;

    private int productCode;


    public CommentDTO(String message, int userCode, int productCode) {
        this.message = message;
        this.productCode = productCode;
        this.userCode = userCode;
    }
}
