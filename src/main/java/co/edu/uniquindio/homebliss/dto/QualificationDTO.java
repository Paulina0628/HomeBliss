package co.edu.uniquindio.homebliss.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class QualificationDTO {

   @NotNull
   private String tittle;

   @NotNull
   private Integer clientId;

   @NotNull
   private Integer productId;

}

