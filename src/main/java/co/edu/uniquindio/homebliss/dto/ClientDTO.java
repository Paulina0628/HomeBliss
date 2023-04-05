package co.edu.uniquindio.homebliss.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

@AllArgsConstructor
@Getter
@Setter
public class ClientDTO {

    @NotNull(message = "El nombre no puede ser null")
    @NotBlank(message = "El nombre no puede estar vacía")
    @Length(max = 50, message = "El nombre debe ser de máximo 50 caracteres")
    private String name;

    @NotNull(message = "El apellido no puede ser null")
    @NotBlank(message = "El apellido no puede estar vacía")
    @Length(max = 50, message = "El apellido debe ser de máximo 50 caracteres")
    private String lastname;

    @NotNull(message = "El teléfono no puede ser null")
    @NotBlank(message = "El teléfono no puede estar vacía")
    @Length(max = 12, message = "El teléfono debe ser de máximo 50 caracteres")
    private String phone;

    @NotNull(message = "La dirección no puede ser null")
    @NotBlank(message = "La dirección no puede estar vacía")
    @Length(max = 50, message = "La dirección debe ser de máximo 50 caracteres")
    private String address;

    @NotNull(message = "El correo no puede ser null")
    @NotBlank(message = "El correo no puede estar vacía")
    @Length(max = 100, message = "El correo debe ser de máximo 100 caracteres")
    private String email;

    @NotNull(message = "La contraseña no puede ser null")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Length(min = 7, max = 50, message = "La contraseña debe ser de máximo 50 caracteres")
    private String password;

    @NotNull(message = "El estado no puede ser null")
    @NotBlank(message = "El estado no puede estar vacía")
    private String state;
}
