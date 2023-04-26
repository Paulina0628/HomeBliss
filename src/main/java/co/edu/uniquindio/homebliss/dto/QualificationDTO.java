package co.edu.uniquindio.homebliss.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class QualificationDTO extends CommentDTO {

   @NotNull
   private String title;

   public QualificationDTO(String message, int userCode, int productCode, String title) {
      super(message, userCode, productCode);
      this.title = title;
   }

}

