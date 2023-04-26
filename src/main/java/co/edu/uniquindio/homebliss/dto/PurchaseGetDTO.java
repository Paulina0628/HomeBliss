package co.edu.uniquindio.homebliss.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class PurchaseGetDTO {

    private int id;

    private int clientCode;

    private LocalDateTime created_date;

    private float total_price;

    private String paymentMethod;

}
