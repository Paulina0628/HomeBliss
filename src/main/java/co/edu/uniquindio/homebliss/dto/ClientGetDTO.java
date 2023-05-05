package co.edu.uniquindio.homebliss.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClientGetDTO {
    private int id;

    private String name;

    private String email;

    private String address;

    private String phone;

}
