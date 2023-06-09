package co.edu.uniquindio.homebliss.dto;

import co.edu.uniquindio.homebliss.model.UserState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class ClientPostDTO {

    @NotNull(message = "El nombre no puede ser null")
    @NotBlank(message = "El nombre no puede estar vacía")
    @Length(max = 100, message = "El nombre debe ser de máximo 50 caracteres")
    private String name;

    @NotNull(message = "El apellido no puede ser null")
    @NotBlank(message = "El apellido no puede estar vacía")
    @Length(max = 100, message = "El apellido debe ser de máximo 50 caracteres")
    private String lastname;

    @NotNull(message = "El teléfono no puede ser null")
    @NotBlank(message = "El teléfono no puede estar vacía")
    @Length(max = 12, message = "El teléfono debe ser de máximo 50 caracteres")
    private String phone;

    @NotNull(message = "La dirección no puede ser null")
    @NotBlank(message = "La dirección no puede estar vacía")
    @Length(max = 100, message = "La dirección debe ser de máximo 50 caracteres")
    private String address;

    @NotNull(message = "El correo no puede ser null")
    @NotBlank(message = "El correo no puede estar vacía")
    @Length(max = 150, message = "El correo debe ser de máximo 100 caracteres")
    @Email(message = "El email no es válido")
    private String email;

    @NotNull(message = "La contraseña no puede ser null")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Length(min = 7, max = 50, message = "La contraseña debe ser de mínimo 7 caracteres y máximo 50 caracteres")
    private String password;

    private UserState state;
}
