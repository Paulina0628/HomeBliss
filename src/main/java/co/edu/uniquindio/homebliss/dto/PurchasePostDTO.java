package co.edu.uniquindio.homebliss.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PurchasePostDTO {

    @NotNull(message = "El usuario no puede ser null")
    @NotBlank(message = "El usuario no puede estar vacía")
    @PositiveOrZero
    private int clientCode;

    @NotNull(message = "El precio total no puede ser nulo")
    private float totalPrice;

    @NotNull(message = "El PaymentMethod no pueden ser null")
    private String paymentMethod;


}
