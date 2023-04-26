package co.edu.uniquindio.homebliss.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @NotNull(message = "Los productos no pueden ser null")
    private List<Integer> productCode;

    @NotNull(message = "Los productos no pueden ser null")
    private List<Integer> productAmount;

    @NotNull(message = "Los productos no pueden ser null")
    private List<Float> productPrice;

}
